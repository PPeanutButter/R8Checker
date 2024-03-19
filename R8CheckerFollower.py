#!/usr/bin/python3
import json

prePath = "/Users/hy/AndroidStudioProjects/weplay/wejoy/"
allClassMapFile = prePath + "annotation_map.json"
suspiciousClassFile = prePath + "suspicious_class_set.json"
basicVarientStr =["I","F","D","J","Z","java/lang/String","java/lang/Object"]

targetClassField = dict()
def readFromJson(path:str):
    with open(path) as f:
        data = json.load(f)
    return data

def isBasicVarient(v :str)->bool:
    for basicV in basicVarientStr:
        if basicV == v:
            return True
    return False 

def splitInnerTypeFromArray(v: str)->str:
    if v.startswith("["):
        return v[1:]
    return v

def splitInnerTypeFromMap(v:str)->str:
    pass


def findClassFieldNoSerialize(allClassMap:dict,c:str):
    if isBasicVarient(c):
        return
    c= c.replace("/",".")
    clazzAndField = allClassMap.get(c)
    if clazzAndField != None :
        targetClassField[c] = clazzAndField
    else:
         print(c + " not found in allClassMap\n")

# com.wepie.wespy.model.entity.other.RecentGame 
if __name__ == "__main__":
    allClassMap = readFromJson(allClassMapFile)
    suspociousClassSet = readFromJson(suspiciousClassFile)
    # for k,v in allClassMap.items():
    #     if "om.wepie.wespy.model.entity.family" in k:
    #         print("find wejoyLoginConfig :"+k+"\n")
    for c in suspociousClassSet:
        findClassFieldNoSerialize(allClassMap,c)
    print("hello")