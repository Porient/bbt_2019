import jieba
import sys

def cut_word(words):
    return jieba.cut(words)

if __name__ == "__main__":
    words = sys.argv[1]
    print(list(cut_word(words)))