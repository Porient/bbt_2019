import torch
import torch.nn as nn
import torch.nn.functional as F
import torch.optim


device = torch.device("cuda:0" if torch.cuda.is_available() else "cpu")
class Phone_model(nn.Module):

    def __init__(self, user_max, phone_max, convParams, embed_dim=32, fc_size=200):

        super(Phone_model, self).__init__()

        # user embeddings
        self.embedding_user_id = nn.Embedding(user_max['user_id'], embed_dim)
        self.embedding_user_gender = nn.Embedding(user_max['user_gender'], embed_dim // 2)
        self.embedding_user_age = nn.Embedding(user_max['user_age'], embed_dim // 2)
        self.embedding_user_job = nn.Embedding(user_max['user_job'], embed_dim // 2)

        # user embeddings to fc
        self.fc_user_id = nn.Linear(embed_dim, embed_dim)
        self.fc_user_gender = nn.Linear(embed_dim // 2, embed_dim)
        self.fc_user_age = nn.Linear(embed_dim // 2, embed_dim)
        self.fc_user_job = nn.Linear(embed_dim // 2, embed_dim)

        # concat embeddings
        self.fc_user_combine = nn.Linear(4 * embed_dim, fc_size)

        # phone embeddings to fc
        self.embedding_phone_id = nn.Embedding(phone_max['phone_id'], embed_dim) 
        self.embedding_phone_type_sum = nn.EmbeddingBag(phone_max['phone_type'], embed_dim, mode='sum')

        self.fc_phone_id = nn.Linear(embed_dim, embed_dim)
        self.fc_phone_type = nn.Linear(embed_dim, embed_dim)

        # phone embedding to fc
        self.fc_phone_id_phone_type = nn.Linear(embed_dim * 2, fc_size)

        # text convolution
        self.embedding_phone_words = nn.Embedding(phone_max['phone_word'], embed_dim)

        kernel_sizes = convParams['kernel_sizes']
        self.Convs_text = [nn.Sequential(
            nn.Conv2d(1, 8, kernel_size=(k, embed_dim)),
            nn.ReLU(),
            nn.MaxPool2d(kernel_size=(15 - k + 1, 1), stride=(1, 1))
        ).to(device) for k in kernel_sizes]

        # phone  concat
        self.fc_phone_combine = nn.Linear(embed_dim * 2 + 8 * len(kernel_sizes), fc_size)  

        # BatchNorm layer
        self.BN = nn.BatchNorm2d(1)

    def forward(self, user_input, phone_input):
        # pack train_data
        user_id = user_input['user_id']
        user_gender = user_input['user_gender']
        user_age = user_input['user_age']
        user_job = user_input['user_job']

        phone_id = phone_input['phone_id']
        phone_type = phone_input['phone_type']
        phone_text = phone_input['phone_text']
        if torch.cuda.is_available():
            user_id, user_gender, user_age, user_job,phone_id,phone_type,phone_text = \
            user_id.to(device), user_gender.to(device), user_age.to(device), user_job.to(device), phone_id.to(device), phone_type.to(device), phone_text.to(device)
        # user
        feature_user_id = self.BN(F.relu(self.fc_user_id(self.embedding_user_id(user_id))))
        feature_user_gender = self.BN(F.relu(self.fc_user_gender(self.embedding_user_gender(user_gender))))
        feature_user_age =  self.BN(F.relu(self.fc_user_age(self.embedding_user_age(user_age))))
        feature_user_job = self.BN(F.relu(self.fc_user_job(self.embedding_user_job(user_job))))

        # feature_user 
        feature_user = F.tanh(self.fc_user_combine(
            torch.cat([feature_user_id, feature_user_gender, feature_user_age, feature_user_job], 3)
        )).view(-1,1,200)

        # phone
        feature_phone_id = self.BN(F.relu(self.fc_phone_id(self.embedding_phone_id(phone_id))))
        feature_phone_type = self.BN(F.relu(self.fc_phone_type(self.embedding_phone_type_sum(phone_type)).view(-1,1,1,32)))

        # text cnn
        feature_img = self.embedding_phone_words(phone_text) 
        flattern_tensors = []
        for conv in self.Convs_text:
            flattern_tensors.append(conv(feature_img.view(-1,1,15,32)).view(-1,1, 8)) 

        feature_flattern_dropout = F.dropout(torch.cat(flattern_tensors,2), p=0.5)  

        # feature_phone 
        feature_phone = F.tanh(self.fc_phone_combine(
            torch.cat([feature_phone_id.view(-1,1,32), feature_phone_type.view(-1,1,32), feature_flattern_dropout], 2)
        ))

        output = torch.sum(feature_user * feature_phone, 2) 
        return output, feature_user, feature_phone

