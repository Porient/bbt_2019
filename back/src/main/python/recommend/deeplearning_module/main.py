from model import Phone_model
from dataset import PhoneRankDataset
from torch.utils.data import DataLoader
import torch
import torch.optim as optim
import torch.nn as nn
from tensorboardX import SummaryWriter
device = torch.device("cuda:0" if torch.cuda.is_available() else "cpu")
user_max={
    'user_id':1000,  
    'user_gender':2,
    'user_age':10,
    'user_job':30
}
phone_max={
    'phone_id':300,  
    'phone_type':10,
    'phone_word':3000  
}

convParams={
    'kernel_sizes':[2,3,4,5]
}


def train(model,num_epochs=5, lr=0.0001):
    loss_function = nn.MSELoss()
    optimizer = optim.Adam(model.parameters(),lr=lr)

    datasets = PhoneRankDataset(pkl_file='dataset.p')
    dataloader = DataLoader(datasets,batch_size=256,shuffle=True)

    writer = SummaryWriter()
    for epoch in range(num_epochs):
        loss_sum = 0
        for i_batch,sample_batch in enumerate(dataloader):

            user_inputs = sample_batch['user_inputs']
            phone_inputs = sample_batch['phone_inputs']
            target = sample_batch['target'].to(device)

            model.zero_grad()

            tag_rank , _ , _ = model(user_inputs, phone_inputs)

            loss = loss_function(tag_rank, target)
            if i_batch%20 ==0:
                writer.add_scalar('data/loss', loss, i_batch*20)
                print(loss)

            loss_sum += loss
            loss.backward()
            optimizer.step()
        print('Epoch {}:\t loss:{}'.format(epoch,loss_sum))
    writer.export_scalars_to_json("./test.json")
    writer.close()



if __name__=='__main__':
    model = Phone_model(user_max=user_max,
                     phone_max=phone_max,
                      convParams=convParams)
    model=model.to(device)

    #train model
    train(model=model,num_epochs=1)
    torch.save(model.state_dict(), 'Params/model_params.pkl')


    model.load_state_dict(torch.load('Params/model_params.pkl'))
    from interface import savePhoneAndUserFeature
    savePhoneAndUserFeature(model=model)

