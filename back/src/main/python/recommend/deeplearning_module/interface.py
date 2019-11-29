# Recommendation Interface

import torch
from torch.utils.data import DataLoader
from dataset import PhoneRankDataset

import numpy as np
import pickle as pkl

def savePhoneAndUserFeature(model):

    batch_size = 256

    datasets = PhoneRankDataset(pkl_file='data.p')
    dataloader = DataLoader(datasets, batch_size=batch_size, shuffle=False,num_workers=4)

    user_feature_dict = {}
    phone_feature_dict = {}
    phones={}
    users = {}
    with torch.no_grad():
        for i_batch, sample_batch in enumerate(dataloader):
            user_inputs = sample_batch['user_inputs']
            phone_inputs = sample_batch['phone_inputs']

            _, feature_user, feature_phone = model(user_inputs, phone_inputs)

            feature_user = feature_user.cpu().numpy()
            feature_phone = feature_phone.cpu().numpy()


            for i in range(user_inputs['user_id'].shape[0]):
                user_id = user_inputs['user_id'][i]   # user_id
                user_gender = user_inputs['user_gender'][i]
                user_age = user_inputs['user_age'][i]
                user_job = user_inputs['user_job'][i]

                phone_id = phone_inputs['phone_id'][i]   # phone_id
                phone_type = phone_inputs['phone_type'][i]
                phone_text = phone_inputs['phone_text'][i]

                if user_id.item() not in users.keys():
                    users[user_id.item()]={'user_id':user_id,'user_gender':user_gender,'user_age':user_age,'user_job':user_job}
                if phone_id.item() not in phones.keys():
                    phones[phone_id.item()]={'phone_id':phone_id,'phone_type':phone_type, 'phone_text':phone_text}

                if user_id not in user_feature_dict.keys():
                    user_feature_dict[user_id]=feature_user[i]
                if phone_id not in phone_feature_dict.keys():
                    phone_feature_dict[phone_id]=feature_phone[i]

    feature_data = {'feature_user': user_feature_dict, 'feature_phone':phone_feature_dict}
    dict_user_phone={'user': users, 'phone':phones}
    pkl.dump(feature_data,open('Params/feature_data.pkl','wb'))
    pkl.dump(dict_user_phone, open('Params/user_phone_dict.pkl','wb'))

def getUserMostLike(user_id):

    feature_data = pkl.load(open('Params/feature_data.pkl', 'rb'))
    user_phone_ids = pkl.load(open('Params/user_phone_dict.pkl','rb'))
    assert user_id in user_phone_ids['user'], \
        'Expect user whose id is user_id exists, but get None'
    if user_id not in user_phone_ids['user']:
        print('id not exist')
        return None
    feature_user = feature_data['feature_user'][user_id]

    phone_dict = user_phone_ids['phone']
    phone_id_rank = {}
    for phone_id in phone_dict.keys():
        feature_phone=feature_data['feature_phone'][phone_id]
        rank = np.dot(feature_user,feature_phone.T)
        if phone_id not in phone_id_rank.keys():
            phone_id_rank[phone_id]=rank.item()

    phone_id_rank = [(phone_id, rank) for phone_id, rank in phone_id_rank.items()]
    phone_ids = [phone_id[0] for phone_id in sorted(phone_id_rank, key=lambda x: x[1], reverse=True)]
    return phone_ids[0]




