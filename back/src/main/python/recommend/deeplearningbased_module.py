#%%
import torch as t
from data_process import data_mining
import random
from deeplearning_module import interface

#%%
def getTopN(user_id, n=1):
    TopNList = []
    mostlike = interface.getUserMostLike(user_id)
    TopNList.append(mostlike)
    return TopNList



#%%