from torch.utils.data import Dataset
import pickle as pkl
import torch
from pandas import DataFrame

class PhoneRankDataset(Dataset):

    def __init__(self, pkl_file):

        self.dataFrame = pkl.load(open(pkl_file,'rb'))
    def __len__(self):
        return len(self.dataFrame)

    def __getitem__(self, idx):

        # user data
        user_id = self.dataFrame.ix[idx]['user_id']
        user_gender = self.dataFrame.ix[idx]['user_user_gender']
        user_age = self.dataFrame.ix[idx]['user_user_age']
        user_job = self.dataFrame.ix[idx]['user_user_job']

        # phone data
        phone_id = self.dataFrame.ix[idx]['phone_id']
        phone_type=self.dataFrame.ix[idx]['phone_type']
        phone_text=self.dataFrame.ix[idx]['phone_title']

        # target
        rank = torch.FloatTensor([self.dataFrame.ix[idx]['rank']])
        user_inputs = {
            'user_id': torch.LongTensor([user_id]).view(1,-1),
            'user_gender': torch.LongTensor([user_gender]).view(1,-1),
            'user_age': torch.LongTensor([user_age]).view(1,-1),
            'user_job': torch.LongTensor([user_job]).view(1,-1)
        }

        phone_inputs = {
            'phone_id': torch.LongTensor([phone_id]).view(1,-1),
            'phone_type': torch.LongTensor(phone_type),
            'phone_text': torch.LongTensor(phone_text)
        }


        sample = {
            'user_inputs': user_inputs,
            'phone_inputs':phone_inputs,
            'target':rank
        }
        return sample
