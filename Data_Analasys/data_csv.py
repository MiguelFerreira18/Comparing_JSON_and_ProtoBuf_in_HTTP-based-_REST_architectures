import numpy as np
import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt
import constants as const

metric_selection = [const.METRIC_NAME, const.TIMESTAP, const.METRIC_VALUE]
metric_row_selection = [const.DATA_SENT,const.DATA_RECEIVED,const.RESPONSE_SIZE,const.REPONSE_SIZE,const.RESPONSE_TIME,const.HTTP_REQ_DURATION]
metric_selection_pods = [const.PKG,const.OTHER]

# Reports JSON

# Create User
json_create_user_1 = pd.read_csv(const.JSON_CREATE_USER_REPORT_PATH_1, usecols=metric_selection)
json_create_user_1 = json_create_user_1[json_create_user_1[const.METRIC_NAME].isin(metric_row_selection)]

json_create_user_2 = pd.read_csv(const.JSON_CREATE_USER_REPORT_PATH_2, usecols=metric_selection)
json_create_user_2 = json_create_user_2[json_create_user_2[const.METRIC_NAME].isin(metric_row_selection)]

json_create_user_3 = pd.read_csv(const.JSON_CREATE_USER_REPORT_PATH_3, usecols=metric_selection)
json_create_user_3 = json_create_user_3[json_create_user_3[const.METRIC_NAME].isin(metric_row_selection)]

json_create_user_pod_1 = pd.read_csv(const.JSON_CREATE_USER_POD_REPORT_PATH_1, usecols=metric_selection_pods)
json_create_user_pod_2 = pd.read_csv(const.JSON_CREATE_USER_POD_REPORT_PATH_2, usecols=metric_selection_pods)
json_create_user_pod_3 = pd.read_csv(const.JSON_CREATE_USER_POD_REPORT_PATH_3, usecols=metric_selection_pods)

# Get all Users
json_get_all_users_1 = pd.read_csv(const.JSON_GET_ALL_USERS_REPORT_PATH_1, usecols=metric_selection)
json_get_all_users_1 = json_get_all_users_1[json_get_all_users_1[const.METRIC_NAME].isin(metric_row_selection)]

json_get_all_users_2 = pd.read_csv(const.JSON_GET_ALL_USERS_REPORT_PATH_2, usecols=metric_selection)
json_get_all_users_2 = json_get_all_users_2[json_get_all_users_2[const.METRIC_NAME].isin(metric_row_selection)]

json_get_all_users_3 = pd.read_csv(const.JSON_GET_ALL_USERS_REPORT_PATH_3, usecols=metric_selection)
json_get_all_users_3 = json_get_all_users_3[json_get_all_users_3[const.METRIC_NAME].isin(metric_row_selection)]

json_get_all_users_pod_1 = pd.read_csv(const.JSON_GET_ALL_USERS_POD_REPORT_PATH_1, usecols=metric_selection_pods)
json_get_all_users_pod_2 = pd.read_csv(const.JSON_GET_ALL_USERS_POD_REPORT_PATH_2, usecols=metric_selection_pods)
json_get_all_users_pod_3 = pd.read_csv(const.JSON_GET_ALL_USERS_POD_REPORT_PATH_3, usecols=metric_selection_pods)

# Get User by ID
json_get_user_id_1 = pd.read_csv(const.JSON_GET_USER_ID_REPORT_PATH_1, usecols=metric_selection)
json_get_user_id_1 = json_get_user_id_1[json_get_user_id_1[const.METRIC_NAME].isin(metric_row_selection)]

json_get_user_id_2 = pd.read_csv(const.JSON_GET_USER_ID_REPORT_PATH_2, usecols=metric_selection)
json_get_user_id_2 = json_get_user_id_2[json_get_user_id_2[const.METRIC_NAME].isin(metric_row_selection)]

json_get_user_id_3 = pd.read_csv(const.JSON_GET_USER_ID_REPORT_PATH_3, usecols=metric_selection)
json_get_user_id_3 = json_get_user_id_3[json_get_user_id_3[const.METRIC_NAME].isin(metric_row_selection)]

json_get_user_id_pod_1 = pd.read_csv(const.JSON_GET_USER_ID_POD_REPORT_PATH_1, usecols=metric_selection_pods)
json_get_user_id_pod_2 = pd.read_csv(const.JSON_GET_USER_ID_POD_REPORT_PATH_2, usecols=metric_selection_pods)
json_get_user_id_pod_3 = pd.read_csv(const.JSON_GET_USER_ID_POD_REPORT_PATH_3, usecols=metric_selection_pods)

# Get all Products entities
json_get_all_product_entities_1 = pd.read_csv(const.JSON_GET_ALL_PRODUCT_ENTITIES_REPORT_PATH_1, usecols=metric_selection)
json_get_all_product_entities_1 = json_get_all_product_entities_1[json_get_all_product_entities_1[const.METRIC_NAME].isin(metric_row_selection)]

json_get_all_product_entities_2 = pd.read_csv(const.JSON_GET_ALL_PRODUCT_ENTITIES_REPORT_PATH_2, usecols=metric_selection)
json_get_all_product_entities_2 = json_get_all_product_entities_2[json_get_all_product_entities_2[const.METRIC_NAME].isin(metric_row_selection)]

json_get_all_product_entities_3 = pd.read_csv(const.JSON_GET_ALL_PRODUCT_ENTITIES_REPORT_PATH_3, usecols=metric_selection)
json_get_all_product_entities_3 = json_get_all_product_entities_3[json_get_all_product_entities_3[const.METRIC_NAME].isin(metric_row_selection)]

json_get_all_product_entities_pod_1 = pd.read_csv(const.JSON_GET_ALL_PRODUCT_ENTITIES_POD_REPORT_PATH_1, usecols=metric_selection_pods)
json_get_all_product_entities_pod_2 = pd.read_csv(const.JSON_GET_ALL_PRODUCT_ENTITIES_POD_REPORT_PATH_2, usecols=metric_selection_pods)
json_get_all_product_entities_pod_3 = pd.read_csv(const.JSON_GET_ALL_PRODUCT_ENTITIES_POD_REPORT_PATH_3, usecols=metric_selection_pods)

# Reports Protobuf

# Create User
protobuf_create_user_1 = pd.read_csv(const.PROTOBUF_CREATE_USER_REPORT_PATH_1, usecols=metric_selection)
protobuf_create_user_1 = protobuf_create_user_1[protobuf_create_user_1[const.METRIC_NAME].isin(metric_row_selection)]

protobuf_create_user_2 = pd.read_csv(const.PROTOBUF_CREATE_USER_REPORT_PATH_2, usecols=metric_selection)
protobuf_create_user_2 = protobuf_create_user_2[protobuf_create_user_2[const.METRIC_NAME].isin(metric_row_selection)]

protobuf_create_user_3 = pd.read_csv(const.PROTOBUF_CREATE_USER_REPORT_PATH_3, usecols=metric_selection)
protobuf_create_user_3 = protobuf_create_user_3[protobuf_create_user_3[const.METRIC_NAME].isin(metric_row_selection)]

protobuf_create_user_pod_1 = pd.read_csv(const.PROTOBUF_CREATE_USER_POD_REPORT_PATH_1, usecols=metric_selection_pods)
protobuf_create_user_pod_2 = pd.read_csv(const.PROTOBUF_CREATE_USER_POD_REPORT_PATH_2, usecols=metric_selection_pods)
protobuf_create_user_pod_3 = pd.read_csv(const.PROTOBUF_CREATE_USER_POD_REPORT_PATH_3, usecols=metric_selection_pods)

# Get all Users
protobuf_get_all_users_1 = pd.read_csv(const.PROTOBUF_GET_ALL_USERS_REPORT_PATH_1, usecols=metric_selection)
protobuf_get_all_users_1 = protobuf_get_all_users_1[protobuf_get_all_users_1[const.METRIC_NAME].isin(metric_row_selection)]

protobuf_get_all_users_2 = pd.read_csv(const.PROTOBUF_GET_ALL_USERS_REPORT_PATH_2, usecols=metric_selection)
protobuf_get_all_users_2 = protobuf_get_all_users_2[protobuf_get_all_users_2[const.METRIC_NAME].isin(metric_row_selection)]

protobuf_get_all_users_3 = pd.read_csv(const.PROTOBUF_GET_ALL_USERS_REPORT_PATH_3, usecols=metric_selection)
protobuf_get_all_users_3 = protobuf_get_all_users_3[protobuf_get_all_users_3[const.METRIC_NAME].isin(metric_row_selection)]

protobuf_get_all_users_pod_1 = pd.read_csv(const.PROTOBUF_GET_ALL_USERS_POD_REPORT_PATH_1, usecols=metric_selection_pods)
protobuf_get_all_users_pod_2 = pd.read_csv(const.PROTOBUF_GET_ALL_USERS_POD_REPORT_PATH_2, usecols=metric_selection_pods)
protobuf_get_all_users_pod_3 = pd.read_csv(const.PROTOBUF_GET_ALL_USERS_POD_REPORT_PATH_3, usecols=metric_selection_pods)

# Get User by ID
protobuf_get_user_id_1 = pd.read_csv(const.PROTOBUF_GET_USER_ID_REPORT_PATH_1, usecols=metric_selection)
protobuf_get_user_id_1 = protobuf_get_user_id_1[protobuf_get_user_id_1[const.METRIC_NAME].isin(metric_row_selection)]

protobuf_get_user_id_2 = pd.read_csv(const.PROTOBUF_GET_USER_ID_REPORT_PATH_2, usecols=metric_selection)
protobuf_get_user_id_2 = protobuf_get_user_id_2[protobuf_get_user_id_2[const.METRIC_NAME].isin(metric_row_selection)]

protobuf_get_user_id_3 = pd.read_csv(const.PROTOBUF_GET_USER_ID_REPORT_PATH_3, usecols=metric_selection)
protobuf_get_user_id_3 = protobuf_get_user_id_3[protobuf_get_user_id_3[const.METRIC_NAME].isin(metric_row_selection)]

protobuf_get_user_id_pod_1 = pd.read_csv(const.PROTOBUF_GET_USER_ID_POD_REPORT_PATH_1, usecols=metric_selection_pods)
protobuf_get_user_id_pod_2 = pd.read_csv(const.PROTOBUF_GET_USER_ID_POD_REPORT_PATH_2, usecols=metric_selection_pods)
protobuf_get_user_id_pod_3 = pd.read_csv(const.PROTOBUF_GET_USER_ID_POD_REPORT_PATH_3, usecols=metric_selection_pods)

# Get all Products entities
protobuf_get_all_product_entities_1 = pd.read_csv(const.PROTOBUF_GET_ALL_PRODUCT_ENTITIES_REPORT_PATH_1, usecols=metric_selection)
protobuf_get_all_product_entities_1 = protobuf_get_all_product_entities_1[protobuf_get_all_product_entities_1[const.METRIC_NAME].isin(metric_row_selection)]

protobuf_get_all_product_entities_2 = pd.read_csv(const.PROTOBUF_GET_ALL_PRODUCT_ENTITIES_REPORT_PATH_2, usecols=metric_selection)
protobuf_get_all_product_entities_2 = protobuf_get_all_product_entities_2[protobuf_get_all_product_entities_2[const.METRIC_NAME].isin(metric_row_selection)]

protobuf_get_all_product_entities_3 = pd.read_csv(const.PROTOBUF_GET_ALL_PRODUCT_ENTITIES_REPORT_PATH_3, usecols=metric_selection)
protobuf_get_all_product_entities_3 = protobuf_get_all_product_entities_3[protobuf_get_all_product_entities_3[const.METRIC_NAME].isin(metric_row_selection)]

protobuf_get_all_product_entities_pod_1 = pd.read_csv(const.PROTOBUF_GET_ALL_PRODUCT_ENTITIES_POD_REPORT_PATH_1, usecols=metric_selection_pods)
protobuf_get_all_product_entities_pod_2 = pd.read_csv(const.PROTOBUF_GET_ALL_PRODUCT_ENTITIES_POD_REPORT_PATH_2, usecols=metric_selection_pods)
protobuf_get_all_product_entities_pod_3 = pd.read_csv(const.PROTOBUF_GET_ALL_PRODUCT_ENTITIES_POD_REPORT_PATH_3, usecols=metric_selection_pods)





print(json_create_user_1.head())
# print(json_create_user_2.head())
# print(json_create_user_3.head())
# print(json_create_user_pod_1.head())
# print(json_create_user_pod_2.head())
# print(json_create_user_pod_3.head())

# print(json_get_all_users_1.head())
# print(json_get_all_users_2.head())
# print(json_get_all_users_3.head())
# print(json_get_all_users_pod_1.head())
# print(json_get_all_users_pod_2.head())
# print(json_get_all_users_pod_3.head())

# print(json_get_user_id_1.head())
# print(json_get_user_id_2.head())
# print(json_get_user_id_3.head())
# print(json_get_user_id_pod_1.head())
# print(json_get_user_id_pod_2.head())
# print(json_get_user_id_pod_3.head())

# print(json_get_all_product_entities_1.head())
# print(json_get_all_product_entities_2.head())
# print(json_get_all_product_entities_3.head())
# print(json_get_all_product_entities_pod_1.head())
# print(json_get_all_product_entities_pod_2.head())
# print(json_get_all_product_entities_pod_3.head())


# Find NaN
# print(json_create_user_1.isnull().sum())
# print("=====================================")
# print(json_create_user_2.isnull().sum())
# print("=====================================")
# print(json_create_user_3.isnull().sum())
# print("=====================================")
# print(json_create_user_pod_1.isnull().sum())
# print("=====================================")
# print(json_create_user_pod_2.isnull().sum())
# print("=====================================")
# print(json_create_user_pod_3.isnull().sum())
# print("=====================================")
# print("=====================================")
# print("=====================================")
# print(json_get_all_users_1.isnull().sum())
# print("=====================================")
# print(json_get_all_users_2.isnull().sum())
# print("=====================================")
# print(json_get_all_users_3.isnull().sum())
# print("=====================================")
# print(json_get_all_users_pod_1.isnull().sum())
# print("=====================================")
# print(json_get_all_users_pod_2.isnull().sum())
# print("=====================================")
# print(json_get_all_users_pod_3.isnull().sum())
# print("=====================================")
# print("=====================================")
# print("=====================================")
# print(json_get_user_id_1.isnull().sum())
# print("=====================================")
# print(json_get_user_id_2.isnull().sum())
# print("=====================================")
# print(json_get_user_id_3.isnull().sum())
# print("=====================================")
# print(json_get_user_id_pod_1.isnull().sum())
# print("=====================================")
# print(json_get_user_id_pod_2.isnull().sum())
# print("=====================================")
# print(json_get_user_id_pod_3.isnull().sum())
# print("=====================================")
# print("=====================================")
# print("=====================================")
# print(json_get_all_product_entities_1.isnull().sum())
# print("=====================================")
# print(json_get_all_product_entities_2.isnull().sum())
# print("=====================================")
# print(json_get_all_product_entities_3.isnull().sum())
# print("=====================================")
# print(json_get_all_product_entities_pod_1.isnull().sum())
# print("=====================================")
# print(json_get_all_product_entities_pod_2.isnull().sum())
# print("=====================================")
# print(json_get_all_product_entities_pod_3.isnull().sum())
# print("=====================================")










# MEAN OF EACH NORMAL REPORT

###! CREATE USERS BOTH JSON AND PROTOBUF

### JSON MEAN FOR RESPONSE TIME
mean_json_create_user_1 = round(json_create_user_1[json_create_user_1[const.METRIC_NAME] == const.RESPONSE_TIME][const.METRIC_VALUE].mean(),2)
mean_json_create_user_2 = round(json_create_user_2[json_create_user_2[const.METRIC_NAME] == const.RESPONSE_TIME][const.METRIC_VALUE].mean(),2)
mean_json_create_user_3 = round(json_create_user_3[json_create_user_3[const.METRIC_NAME] == const.RESPONSE_TIME][const.METRIC_VALUE].mean(),2)
total_mean_json_create_user = round((mean_json_create_user_1 + mean_json_create_user_2 + mean_json_create_user_3)/3,2)

### JSON MEAN FOR RESPONSE SIZE
mean_response_size_json_create_user_1 = round(json_create_user_1[json_create_user_1[const.METRIC_NAME] == const.REPONSE_SIZE][const.METRIC_VALUE].mean(),2)
mean_response_size_json_create_user_2 = round(json_create_user_2[json_create_user_2[const.METRIC_NAME] == const.REPONSE_SIZE][const.METRIC_VALUE].mean(),2)
mean_response_size_json_create_user_3 = round(json_create_user_3[json_create_user_3[const.METRIC_NAME] == const.REPONSE_SIZE][const.METRIC_VALUE].mean(),2)
total_mean_response_size_json_create_user = round((mean_response_size_json_create_user_1 + mean_response_size_json_create_user_2 + mean_response_size_json_create_user_3)/3,2)

### PROTOBUF MEAN FOR RESPONSE TIME
mean_protobuf_create_user_1 = round(protobuf_create_user_1[protobuf_create_user_1[const.METRIC_NAME] == const.RESPONSE_TIME][const.METRIC_VALUE].mean(),2)
mean_protobuf_create_user_2 = round(protobuf_create_user_2[protobuf_create_user_2[const.METRIC_NAME] == const.RESPONSE_TIME][const.METRIC_VALUE].mean(),2)
mean_protobuf_create_user_3 = round(protobuf_create_user_3[protobuf_create_user_3[const.METRIC_NAME] == const.RESPONSE_TIME][const.METRIC_VALUE].mean(),2)
total_mean_protobuf_create_user = round((mean_protobuf_create_user_1 + mean_protobuf_create_user_2 + mean_protobuf_create_user_3)/3,2)

### PROTOBUF MEAN FOR RESPONSE SIZE
mean_response_size_protobuf_create_user_1 = round(protobuf_create_user_1[protobuf_create_user_1[const.METRIC_NAME] == const.REPONSE_SIZE][const.METRIC_VALUE].mean(),2)
mean_response_size_protobuf_create_user_2 = round(protobuf_create_user_2[protobuf_create_user_2[const.METRIC_NAME] == const.REPONSE_SIZE][const.METRIC_VALUE].mean(),2)
mean_response_size_protobuf_create_user_3 = round(protobuf_create_user_3[protobuf_create_user_3[const.METRIC_NAME] == const.REPONSE_SIZE][const.METRIC_VALUE].mean(),2)
total_mean_response_size_protobuf_create_user = round((mean_response_size_protobuf_create_user_1 + mean_response_size_protobuf_create_user_2 + mean_response_size_protobuf_create_user_3)/3,2)


print("=====================================\n")
print("MEAN OF EACH NORMAL REPORT - JSON \n")
print("Mean JSON Create User 1: ", mean_json_create_user_1, " Mean Response Size JSON Create User 1: ", mean_response_size_json_create_user_1)
print("Mean JSON Create User 2: ", mean_json_create_user_2, " Mean Response Size JSON Create User 2: ", mean_response_size_json_create_user_2)
print("Mean JSON Create User 3: ", mean_json_create_user_3, " Mean Response Size JSON Create User 3: ", mean_response_size_json_create_user_3)
print("=====================================\n")
print("MEAN OF EACH NORMAL REPORT - PROTOBUF \n")
print("Mean Protobuf Create User 1: ", mean_protobuf_create_user_1, " Mean Response Size Protobuf Create User 1: ", mean_response_size_protobuf_create_user_1)
print("Mean Protobuf Create User 2: ", mean_protobuf_create_user_2, " Mean Response Size Protobuf Create User 2: ", mean_response_size_protobuf_create_user_2)
print("Mean Protobuf Create User 3: ", mean_protobuf_create_user_3, " Mean Response Size Protobuf Create User 3: ", mean_response_size_protobuf_create_user_3)
print("=====================================\n")
print("TOTAL MEAN COMPARISON \n")
print("Mean JSON Create User: ", total_mean_json_create_user, " Mean Response Size JSON Create User: ", total_mean_response_size_json_create_user)
print("Mean Protobuf Create User: ", total_mean_protobuf_create_user, " Mean Response Size Protobuf Create User: ", total_mean_response_size_protobuf_create_user)
print("=====================================\n")

####! GET ALL USERS BOTH JSON AND PROTOBUF

### JSON MEAN FOR RESPONSE TIME
mean_json_get_all_users_1 = round(json_get_all_users_1[json_get_all_users_1[const.METRIC_NAME] == const.RESPONSE_TIME][const.METRIC_VALUE].mean(),2)
mean_json_get_all_users_2 = round(json_get_all_users_2[json_get_all_users_2[const.METRIC_NAME] == const.RESPONSE_TIME][const.METRIC_VALUE].mean(),2)
mean_json_get_all_users_3 = round(json_get_all_users_3[json_get_all_users_3[const.METRIC_NAME] == const.RESPONSE_TIME][const.METRIC_VALUE].mean(),2)
total_mean_json_get_all_users = round((mean_json_get_all_users_1 + mean_json_get_all_users_2 + mean_json_get_all_users_3)/3,2)

### JSON MEAN FOR RESPONSE SIZE
mean_response_size_json_get_all_users_1 = round(json_get_all_users_1[json_get_all_users_1[const.METRIC_NAME] == const.RESPONSE_SIZE][const.METRIC_VALUE].mean(),2)
mean_response_size_json_get_all_users_2 = round(json_get_all_users_2[json_get_all_users_2[const.METRIC_NAME] == const.RESPONSE_SIZE][const.METRIC_VALUE].mean(),2)
mean_response_size_json_get_all_users_3 = round(json_get_all_users_3[json_get_all_users_3[const.METRIC_NAME] == const.RESPONSE_SIZE][const.METRIC_VALUE].mean(),2)
total_mean_response_size_json_get_all_users = round((mean_response_size_json_get_all_users_1 + mean_response_size_json_get_all_users_2 + mean_response_size_json_get_all_users_3)/3,2)

### PROTOBUF MEAN FOR RESPONSE TIME
mean_protobuf_get_all_users_1 = round(protobuf_get_all_users_1[protobuf_get_all_users_1[const.METRIC_NAME] == const.RESPONSE_TIME][const.METRIC_VALUE].mean(),2)   
mean_protobuf_get_all_users_2 = round(protobuf_get_all_users_2[protobuf_get_all_users_2[const.METRIC_NAME] == const.RESPONSE_TIME][const.METRIC_VALUE].mean(),2)
mean_protobuf_get_all_users_3 = round(protobuf_get_all_users_3[protobuf_get_all_users_3[const.METRIC_NAME] == const.RESPONSE_TIME][const.METRIC_VALUE].mean(),2)
total_mean_protobuf_get_all_users = round((mean_protobuf_get_all_users_1 + mean_protobuf_get_all_users_2 + mean_protobuf_get_all_users_3)/3,2)

### PROTOBUF MEAN FOR RESPONSE SIZE
mean_response_size_protobuf_get_all_users_1 = round(protobuf_get_all_users_1[protobuf_get_all_users_1[const.METRIC_NAME] == const.RESPONSE_SIZE][const.METRIC_VALUE].mean(),2)
mean_response_size_protobuf_get_all_users_2 = round(protobuf_get_all_users_2[protobuf_get_all_users_2[const.METRIC_NAME] == const.RESPONSE_SIZE][const.METRIC_VALUE].mean(),2)
mean_response_size_protobuf_get_all_users_3 = round(protobuf_get_all_users_3[protobuf_get_all_users_3[const.METRIC_NAME] == const.RESPONSE_SIZE][const.METRIC_VALUE].mean(),2)
total_mean_response_size_protobuf_get_all_users = round((mean_response_size_protobuf_get_all_users_1 + mean_response_size_protobuf_get_all_users_2 + mean_response_size_protobuf_get_all_users_3)/3,2)

print("=====================================\n")
print("MEAN OF EACH NORMAL REPORT - JSON \n")
print("Mean JSON Get All Users 1: ", mean_json_get_all_users_1, " Mean Response Size JSON Get All Users 1: ", mean_response_size_json_get_all_users_1)
print("Mean JSON Get All Users 2: ", mean_json_get_all_users_2, " Mean Response Size JSON Get All Users 2: ", mean_response_size_json_get_all_users_2)
print("Mean JSON Get All Users 3: ", mean_json_get_all_users_3, " Mean Response Size JSON Get All Users 3: ", mean_response_size_json_get_all_users_3)
print("=====================================\n")
print("MEAN OF EACH NORMAL REPORT - PROTOBUF \n")
print("Mean Protobuf Get All Users 1: ", mean_protobuf_get_all_users_1, " Mean Response Size Protobuf Get All Users 1: ", mean_response_size_protobuf_get_all_users_1)
print("Mean Protobuf Get All Users 2: ", mean_protobuf_get_all_users_2, " Mean Response Size Protobuf Get All Users 2: ", mean_response_size_protobuf_get_all_users_2)
print("Mean Protobuf Get All Users 3: ", mean_protobuf_get_all_users_3, " Mean Response Size Protobuf Get All Users 3: ", mean_response_size_protobuf_get_all_users_3)
print("=====================================\n")
print("TOTAL MEAN COMPARISON \n")
print("Mean JSON Get All Users: ", total_mean_json_get_all_users, " Mean Response Size JSON Get All Users: ", total_mean_response_size_json_get_all_users)
print("Mean Protobuf Get All Users: ", total_mean_protobuf_get_all_users, " Mean Response Size Protobuf Get All Users: ", total_mean_response_size_protobuf_get_all_users)
print("=====================================\n")

####! GET USER BY ID BOTH JSON AND PROTOBUF

### JSON MEAN FOR RESPONSE TIME
mean_json_get_user_id_1 = round(json_get_user_id_1[json_get_user_id_1[const.METRIC_NAME] == const.RESPONSE_TIME][const.METRIC_VALUE].mean(),2)
mean_json_get_user_id_2 = round(json_get_user_id_2[json_get_user_id_2[const.METRIC_NAME] == const.RESPONSE_TIME][const.METRIC_VALUE].mean(),2)
mean_json_get_user_id_3 = round(json_get_user_id_3[json_get_user_id_3[const.METRIC_NAME] == const.RESPONSE_TIME][const.METRIC_VALUE].mean(),2)
total_mean_json_get_user_id = round((mean_json_get_user_id_1 + mean_json_get_user_id_2 + mean_json_get_user_id_3)/3,2)

### JSON MEAN FOR RESPONSE SIZE
mean_response_size_json_get_user_id_1 = round(json_get_user_id_1[json_get_user_id_1[const.METRIC_NAME] == const.RESPONSE_SIZE][const.METRIC_VALUE].mean(),2)
mean_response_size_json_get_user_id_2 = round(json_get_user_id_2[json_get_user_id_2[const.METRIC_NAME] == const.RESPONSE_SIZE][const.METRIC_VALUE].mean(),2)
mean_response_size_json_get_user_id_3 = round(json_get_user_id_3[json_get_user_id_3[const.METRIC_NAME] == const.RESPONSE_SIZE][const.METRIC_VALUE].mean(),2)
total_mean_response_size_json_get_user_id = round((mean_response_size_json_get_user_id_1 + mean_response_size_json_get_user_id_2 + mean_response_size_json_get_user_id_3)/3,2)

### PROTOBUF MEAN FOR RESPONSE TIME
mean_protobuf_get_user_id_1 = round(protobuf_get_user_id_1[protobuf_get_user_id_1[const.METRIC_NAME] == const.RESPONSE_TIME][const.METRIC_VALUE].mean(),2)
mean_protobuf_get_user_id_2 = round(protobuf_get_user_id_2[protobuf_get_user_id_2[const.METRIC_NAME] == const.RESPONSE_TIME][const.METRIC_VALUE].mean(),2)
mean_protobuf_get_user_id_3 = round(protobuf_get_user_id_3[protobuf_get_user_id_3[const.METRIC_NAME] == const.RESPONSE_TIME][const.METRIC_VALUE].mean(),2)
total_mean_protobuf_get_user_id = round((mean_protobuf_get_user_id_1 + mean_protobuf_get_user_id_2 + mean_protobuf_get_user_id_3)/3,2)

### PROTOBUF MEAN FOR RESPONSE SIZE
mean_response_size_protobuf_get_user_id_1 = round(protobuf_get_user_id_1[protobuf_get_user_id_1[const.METRIC_NAME] == const.RESPONSE_SIZE][const.METRIC_VALUE].mean(),2)
mean_response_size_protobuf_get_user_id_2 = round(protobuf_get_user_id_2[protobuf_get_user_id_2[const.METRIC_NAME] == const.RESPONSE_SIZE][const.METRIC_VALUE].mean(),2)
mean_response_size_protobuf_get_user_id_3 = round(protobuf_get_user_id_3[protobuf_get_user_id_3[const.METRIC_NAME] == const.RESPONSE_SIZE][const.METRIC_VALUE].mean(),2)
total_mean_response_size_protobuf_get_user_id = round((mean_response_size_protobuf_get_user_id_1 + mean_response_size_protobuf_get_user_id_2 + mean_response_size_protobuf_get_user_id_3)/3,2)

print("=====================================\n")
print("MEAN OF EACH NORMAL REPORT - JSON \n")
print("Mean JSON Get User ID 1: ", mean_json_get_user_id_1, " Mean Response Size JSON Get User ID 1: ", mean_response_size_json_get_user_id_1)
print("Mean JSON Get User ID 2: ", mean_json_get_user_id_2, " Mean Response Size JSON Get User ID 2: ", mean_response_size_json_get_user_id_2)
print("Mean JSON Get User ID 3: ", mean_json_get_user_id_3, " Mean Response Size JSON Get User ID 3: ", mean_response_size_json_get_user_id_3)
print("=====================================\n")
print("MEAN OF EACH NORMAL REPORT - PROTOBUF \n")
print("Mean Protobuf Get User ID 1: ", mean_protobuf_get_user_id_1, " Mean Response Size Protobuf Get User ID 1: ", mean_response_size_protobuf_get_user_id_1)
print("Mean Protobuf Get User ID 2: ", mean_protobuf_get_user_id_2, " Mean Response Size Protobuf Get User ID 2: ", mean_response_size_protobuf_get_user_id_2)
print("Mean Protobuf Get User ID 3: ", mean_protobuf_get_user_id_3, " Mean Response Size Protobuf Get User ID 3: ", mean_response_size_protobuf_get_user_id_3)
print("=====================================\n")
print("TOTAL MEAN COMPARISON \n")
print("Mean JSON Get User ID: ", total_mean_json_get_user_id, " Mean Response Size JSON Get User ID: ", total_mean_response_size_json_get_user_id)
print("Mean Protobuf Get User ID: ", total_mean_protobuf_get_user_id, " Mean Response Size Protobuf Get User ID: ", total_mean_response_size_protobuf_get_user_id)
print("=====================================\n")



####! GET ALL PRODUCT ENTITIES BOTH JSON AND PROTOBUF

### JSON MEAN FOR RESPONSE TIME
mean_json_get_all_product_entities_1 = round(json_get_all_product_entities_1[json_get_all_product_entities_1[const.METRIC_NAME] == const.RESPONSE_TIME][const.METRIC_VALUE].mean(),2)
mean_json_get_all_product_entities_2 = round(json_get_all_product_entities_2[json_get_all_product_entities_2[const.METRIC_NAME] == const.RESPONSE_TIME][const.METRIC_VALUE].mean(),2)
mean_json_get_all_product_entities_3 = round(json_get_all_product_entities_3[json_get_all_product_entities_3[const.METRIC_NAME] == const.RESPONSE_TIME][const.METRIC_VALUE].mean(),2)
total_mean_json_get_all_product_entities = round((mean_json_get_all_product_entities_1 + mean_json_get_all_product_entities_2 + mean_json_get_all_product_entities_3)/3,2)

### JSON MEAN FOR RESPONSE SIZE
mean_response_size_json_get_all_product_entities_1 = round(json_get_all_product_entities_1[json_get_all_product_entities_1[const.METRIC_NAME] == const.RESPONSE_SIZE][const.METRIC_VALUE].mean(),2)
mean_response_size_json_get_all_product_entities_2 = round(json_get_all_product_entities_2[json_get_all_product_entities_2[const.METRIC_NAME] == const.RESPONSE_SIZE][const.METRIC_VALUE].mean(),2)
mean_response_size_json_get_all_product_entities_3 = round(json_get_all_product_entities_3[json_get_all_product_entities_3[const.METRIC_NAME] == const.RESPONSE_SIZE][const.METRIC_VALUE].mean(),2)
total_mean_response_size_json_get_all_product_entities = round((mean_response_size_json_get_all_product_entities_1 + mean_response_size_json_get_all_product_entities_2 + mean_response_size_json_get_all_product_entities_3)/3,2)

### PROTOBUF MEAN FOR RESPONSE TIME
mean_protobuf_get_all_product_entities_1 = round(protobuf_get_all_product_entities_1[protobuf_get_all_product_entities_1[const.METRIC_NAME] == const.RESPONSE_TIME][const.METRIC_VALUE].mean(),2)
mean_protobuf_get_all_product_entities_2 = round(protobuf_get_all_product_entities_2[protobuf_get_all_product_entities_2[const.METRIC_NAME] == const.RESPONSE_TIME][const.METRIC_VALUE].mean(),2)
mean_protobuf_get_all_product_entities_3 = round(protobuf_get_all_product_entities_3[protobuf_get_all_product_entities_3[const.METRIC_NAME] == const.RESPONSE_TIME][const.METRIC_VALUE].mean(),2)
total_mean_protobuf_get_all_product_entities = round((mean_protobuf_get_all_product_entities_1 + mean_protobuf_get_all_product_entities_2 + mean_protobuf_get_all_product_entities_3)/3,2)

### PROTOBUF MEAN FOR RESPONSE SIZE
mean_response_size_protobuf_get_all_product_entities_1 = round(protobuf_get_all_product_entities_1[protobuf_get_all_product_entities_1[const.METRIC_NAME] == const.RESPONSE_SIZE][const.METRIC_VALUE].mean(),2)
mean_response_size_protobuf_get_all_product_entities_2 = round(protobuf_get_all_product_entities_2[protobuf_get_all_product_entities_2[const.METRIC_NAME] == const.RESPONSE_SIZE][const.METRIC_VALUE].mean(),2)
mean_response_size_protobuf_get_all_product_entities_3 = round(protobuf_get_all_product_entities_3[protobuf_get_all_product_entities_3[const.METRIC_NAME] == const.RESPONSE_SIZE][const.METRIC_VALUE].mean(),2)
total_mean_response_size_protobuf_get_all_product_entities = round((mean_response_size_protobuf_get_all_product_entities_1 + mean_response_size_protobuf_get_all_product_entities_2 + mean_response_size_protobuf_get_all_product_entities_3)/3,2)

print("=====================================\n")
print("MEAN OF EACH NORMAL REPORT - JSON \n")
print("Mean JSON Get All Product Entities 1: ", mean_json_get_all_product_entities_1, " Mean Response Size JSON Get All Product Entities 1: ", mean_response_size_json_get_all_product_entities_1)
print("Mean JSON Get All Product Entities 2: ", mean_json_get_all_product_entities_2, " Mean Response Size JSON Get All Product Entities 2: ", mean_response_size_json_get_all_product_entities_2)
print("Mean JSON Get All Product Entities 3: ", mean_json_get_all_product_entities_3, " Mean Response Size JSON Get All Product Entities 3: ", mean_response_size_json_get_all_product_entities_3)
print("=====================================\n")
print("MEAN OF EACH NORMAL REPORT - PROTOBUF \n")
print("Mean Protobuf Get All Product Entities 1: ", mean_protobuf_get_all_product_entities_1, " Mean Response Size Protobuf Get All Product Entities 1: ", mean_response_size_protobuf_get_all_product_entities_1)
print("Mean Protobuf Get All Product Entities 2: ", mean_protobuf_get_all_product_entities_2, " Mean Response Size Protobuf Get All Product Entities 2: ", mean_response_size_protobuf_get_all_product_entities_2)
print("Mean Protobuf Get All Product Entities 3: ", mean_protobuf_get_all_product_entities_3, " Mean Response Size Protobuf Get All Product Entities 3: ", mean_response_size_protobuf_get_all_product_entities_3)
print("=====================================\n")
print("TOTAL MEAN COMPARISON \n")
print("Mean JSON Get All Product Entities: ", total_mean_json_get_all_product_entities, " Mean Response Size JSON Get All Product Entities: ", total_mean_response_size_json_get_all_product_entities)
print("Mean Protobuf Get All Product Entities: ", total_mean_protobuf_get_all_product_entities, " Mean Response Size Protobuf Get All Product Entities: ", total_mean_response_size_protobuf_get_all_product_entities)



##! MEAN OF EACH POD REPORT

###! CREATE USERS BOTH JSON AND PROTOBUF

### JSON MEAN FOR THE TOTAL WATTS
mean_json_create_user_pod_1 = round(json_create_user_pod_1[[const.PKG,const.OTHER]].sum(axis=1).mean(),2)
mean_json_create_user_pod_2 = round(json_create_user_pod_2[[const.PKG,const.OTHER]].sum(axis=1).mean(),2)
mean_json_create_user_pod_3 = round(json_create_user_pod_3[[const.PKG,const.OTHER]].sum(axis=1).mean(),2)
total_mean_json_create_user_pod = round((mean_json_create_user_pod_1 + mean_json_create_user_pod_2 + mean_json_create_user_pod_3)/3,2)

### PROTOBUF MEAN FOR THE TOTAL WATTS
mean_protobuf_create_user_pod_1 = round(protobuf_create_user_pod_1[[const.PKG,const.OTHER]].sum(axis=1).mean(),2)
mean_protobuf_create_user_pod_2 = round(protobuf_create_user_pod_2[[const.PKG,const.OTHER]].sum(axis=1).mean(),2)
mean_protobuf_create_user_pod_3 = round(protobuf_create_user_pod_3[[const.PKG,const.OTHER]].sum(axis=1).mean(),2)
total_mean_protobuf_create_user_pod = round((mean_protobuf_create_user_pod_1 + mean_protobuf_create_user_pod_2 + mean_protobuf_create_user_pod_3)/3,2)

print("=====================================\n")
print("MEAN OF EACH POD REPORT - JSON \n")
print("Mean JSON Create User Pod 1: ", mean_json_create_user_pod_1) 
print("Mean JSON Create User Pod 2: ", mean_json_create_user_pod_2) 
print("Mean JSON Create User Pod 3: ", mean_json_create_user_pod_3) 
print("=====================================\n")
print("MEAN OF EACH POD REPORT - PROTOBUF \n")
print("Mean Protobuf Create User Pod 1: ", mean_protobuf_create_user_pod_1) 
print("Mean Protobuf Create User Pod 2: ", mean_protobuf_create_user_pod_2) 
print("Mean Protobuf Create User Pod 3: ", mean_protobuf_create_user_pod_3) 
print("=====================================\n")
print("TOTAL MEAN COMPARISON \n")
print("Mean JSON Create User Pod: ", total_mean_json_create_user_pod)
print("Mean Protobuf Create User Pod: ", total_mean_protobuf_create_user_pod)
print("=====================================\n")

###! GET ALL USERS BOTH JSON AND PROTOBUF

### JSON MEAN FOR THE TOTAL WATTS
mean_json_get_all_users_pod_1 = round(json_get_all_users_pod_1[[const.PKG,const.OTHER]].sum(axis=1).mean(),2)
mean_json_get_all_users_pod_2 = round(json_get_all_users_pod_2[[const.PKG,const.OTHER]].sum(axis=1).mean(),2)
mean_json_get_all_users_pod_3 = round(json_get_all_users_pod_3[[const.PKG,const.OTHER]].sum(axis=1).mean(),2)
total_mean_json_get_all_user_pod= round((mean_json_get_all_users_pod_1 + mean_json_get_all_users_pod_2 + mean_json_get_all_users_pod_3)/3,2)

### PROTOBUF MEAN FOR THE TOTAL WATTS

mean_protobuf_get_all_users_pod_1 = round(protobuf_get_all_users_pod_1[[const.PKG,const.OTHER]].sum(axis=1).mean(),2)
mean_protobuf_get_all_users_pod_2 = round(protobuf_get_all_users_pod_2[[const.PKG,const.OTHER]].sum(axis=1).mean(),2)
mean_protobuf_get_all_users_pod_3 = round(protobuf_get_all_users_pod_3[[const.PKG,const.OTHER]].sum(axis=1).mean(),2)
total_mean_protobuf_get_all_user_pod= round((mean_protobuf_get_all_users_pod_1 + mean_protobuf_get_all_users_pod_2 + mean_protobuf_get_all_users_pod_3)/3,2)

print("=====================================\n")
print("MEAN OF EACH POD REPORT - JSON \n")
print("Mean JSON Get All Users Pod 1: ", mean_json_get_all_users_pod_1) 
print("Mean JSON Get All Users Pod 2: ", mean_json_get_all_users_pod_2) 
print("Mean JSON Get All Users Pod 3: ", mean_json_get_all_users_pod_3)
print("=====================================\n")
print("MEAN OF EACH POD REPORT - PROTOBUF \n")
print("Mean Protobuf Get All Users Pod 1: ", mean_protobuf_get_all_users_pod_1) 
print("Mean Protobuf Get All Users Pod 2: ", mean_protobuf_get_all_users_pod_2) 
print("Mean Protobuf Get All Users Pod 3: ", mean_protobuf_get_all_users_pod_3)
print("=====================================\n")
print("TOTAL MEAN COMPARISON \n")
print("Mean JSON Get All Users Pod: ", total_mean_json_get_all_user_pod)
print("Mean Protobuf Get All Users Pod: ", total_mean_protobuf_get_all_user_pod)
print("=====================================\n")

###! GET USER ID

### JSON MEAN FOR THE TOTAL WATTS
mean_json_get_user_id_pod_1 = round(json_get_user_id_pod_1[[const.PKG,const.OTHER]].sum(axis=1).mean(),2)
mean_json_get_user_id_pod_2 = round(json_get_user_id_pod_2[[const.PKG,const.OTHER]].sum(axis=1).mean(),2)
mean_json_get_user_id_pod_3 = round(json_get_user_id_pod_3[[const.PKG,const.OTHER]].sum(axis=1).mean(),2)
total_mean_json_get_user_id_pod = round((mean_json_get_user_id_pod_1 + mean_json_get_user_id_pod_2 + mean_json_get_user_id_pod_3)/3,2)

### PROTOBUF MEAN FOR THE TOTAL WATTS
mean_protobuf_get_user_id_pod_1 = round(protobuf_get_user_id_pod_1[[const.PKG,const.OTHER]].sum(axis=1).mean(),2)
mean_protobuf_get_user_id_pod_2 = round(protobuf_get_user_id_pod_2[[const.PKG,const.OTHER]].sum(axis=1).mean(),2)
mean_protobuf_get_user_id_pod_3 = round(protobuf_get_user_id_pod_3[[const.PKG,const.OTHER]].sum(axis=1).mean(),2)
total_mean_protobuf_get_user_id_pod = round((mean_protobuf_get_user_id_pod_1 + mean_protobuf_get_user_id_pod_2 + mean_protobuf_get_user_id_pod_3)/3,2)

print("=====================================\n")
print("MEAN OF EACH POD REPORT - JSON \n")
print("Mean JSON Get User ID Pod 1: ", mean_json_get_user_id_pod_1)
print("Mean JSON Get User ID Pod 2: ", mean_json_get_user_id_pod_2)
print("Mean JSON Get User ID Pod 3: ", mean_json_get_user_id_pod_3)
print("=====================================\n")
print("MEAN OF EACH POD REPORT - PROTOBUF \n")
print("Mean Protobuf Get User ID Pod 1: ", mean_protobuf_get_user_id_pod_1)
print("Mean Protobuf Get User ID Pod 2: ", mean_protobuf_get_user_id_pod_2)
print("Mean Protobuf Get User ID Pod 3: ", mean_protobuf_get_user_id_pod_3)
print("=====================================\n")
print("TOTAL MEAN COMPARISON \n")
print("Mean JSON Get User ID Pod: ", total_mean_json_get_user_id_pod)
print("Mean Protobuf Get User ID Pod: ", total_mean_protobuf_get_user_id_pod)
print("=====================================\n")

###! GET ALL PRODUCT ENTITIES

### JSON MEAN FOR THE TOTAL WATTS
mean_json_get_all_product_entities_pod_1 = round(json_get_all_product_entities_pod_1[[const.PKG,const.OTHER]].sum(axis=1).mean(),2)
mean_json_get_all_product_entities_pod_2 = round(json_get_all_product_entities_pod_2[[const.PKG,const.OTHER]].sum(axis=1).mean(),2)
mean_json_get_all_product_entities_pod_3 = round(json_get_all_product_entities_pod_3[[const.PKG,const.OTHER]].sum(axis=1).mean(),2)
total_mean_json_get_all_product_entities_pod = round((mean_json_get_all_product_entities_pod_1 + mean_json_get_all_product_entities_pod_2 + mean_json_get_all_product_entities_pod_3)/3,2)

### PROTOBUF MEAN FOR THE TOTAL WATTS
mean_protobuf_get_all_product_entities_pod_1 = round(protobuf_get_all_product_entities_pod_1[[const.PKG,const.OTHER]].sum(axis=1).mean(),2)
mean_protobuf_get_all_product_entities_pod_2 = round(protobuf_get_all_product_entities_pod_2[[const.PKG,const.OTHER]].sum(axis=1).mean(),2)
mean_protobuf_get_all_product_entities_pod_3 = round(protobuf_get_all_product_entities_pod_3[[const.PKG,const.OTHER]].sum(axis=1).mean(),2)
total_mean_protobuf_get_all_product_entities_pod = round((mean_protobuf_get_all_product_entities_pod_1 + mean_protobuf_get_all_product_entities_pod_2 + mean_protobuf_get_all_product_entities_pod_3)/3,2)

print("=====================================\n")
print("MEAN OF EACH POD REPORT - JSON \n")
print("Mean JSON Get All Product Entities Pod 1: ", mean_json_get_all_product_entities_pod_1)
print("Mean JSON Get All Product Entities Pod 2: ", mean_json_get_all_product_entities_pod_2)
print("Mean JSON Get All Product Entities Pod 3: ", mean_json_get_all_product_entities_pod_3)
print("=====================================\n")
print("MEAN OF EACH POD REPORT - PROTOBUF \n")
print("Mean Protobuf Get All Product Entities Pod 1: ", mean_protobuf_get_all_product_entities_pod_1)
print("Mean Protobuf Get All Product Entities Pod 2: ", mean_protobuf_get_all_product_entities_pod_2)
print("Mean Protobuf Get All Product Entities Pod 3: ", mean_protobuf_get_all_product_entities_pod_3)
print("=====================================\n")
print("TOTAL MEAN COMPARISON \n")
print("Mean JSON Get All Product Entities Pod: ", total_mean_json_get_all_product_entities_pod)
print("Mean Protobuf Get All Product Entities Pod: ", total_mean_protobuf_get_all_product_entities_pod)
print("=====================================\n")


#! CREATE DATAFRAMES FOR EACH NORMAL REPORT

###! CREATE USERS BOTH JSON AND PROTOBUF
create_users_dataframe = pd.DataFrame({
    'Report': ['Report 1', 'Report 2', 'Report 3'],
    'JSON': [mean_json_create_user_1, mean_json_create_user_2, mean_json_create_user_3],
    'Protobuf': [mean_protobuf_create_user_1, mean_protobuf_create_user_2, mean_protobuf_create_user_3]
})


###! GET ALL USERS BOTH JSON AND PROTOBUF
create_get_all_users_dataframe = pd.DataFrame({
    'Report': ['Report 1', 'Report 2', 'Report 3'],
    'JSON': [mean_json_get_all_users_1, mean_json_get_all_users_2, mean_json_get_all_users_3],
    'Protobuf': [mean_protobuf_get_all_users_1, mean_protobuf_get_all_users_2, mean_protobuf_get_all_users_3]
})

###! GET USER BY ID BOTH JSON AND PROTOBUF
create_user_id_dataframe = pd.DataFrame({
    'Report': ['Report 1', 'Report 2', 'Report 3'],
    'JSON': [mean_json_get_user_id_1, mean_json_get_user_id_2, mean_json_get_user_id_3],
    'Protobuf': [mean_protobuf_get_user_id_1, mean_protobuf_get_user_id_2, mean_protobuf_get_user_id_3]
})

###! GET ALL PRODUCT ENTITIES BOTH JSON AND PROTOBUF
create_all_product_entities_dataframe = pd.DataFrame({
    'Report': ['Report 1', 'Report 2', 'Report 3'],
    'JSON': [mean_json_get_all_product_entities_1, mean_json_get_all_product_entities_2, mean_json_get_all_product_entities_3],
    'Protobuf': [mean_protobuf_get_all_product_entities_1, mean_protobuf_get_all_product_entities_2, mean_protobuf_get_all_product_entities_3]
})

#! CREATE DATAFRAMES FOR EACH POD REPORT

###! CREATE USERS BOTH JSON AND PROTOBUF

create_users_pod_dataframe = pd.DataFrame({
    'Report': ['Report 1', 'Report 2', 'Report 3'],
    'JSON': [mean_json_create_user_pod_1, mean_json_create_user_pod_2, mean_json_create_user_pod_3],
    'Protobuf': [mean_protobuf_create_user_pod_1, mean_protobuf_create_user_pod_2, mean_protobuf_create_user_pod_3]
})

###! GET ALL USERS BOTH JSON AND PROTOBUF

get_all_users_pod_dataframe = pd.DataFrame({
    'Report': ['Report 1', 'Report 2', 'Report 3'],
    'JSON': [mean_json_get_all_users_pod_1, mean_json_get_all_users_pod_2, mean_json_get_all_users_pod_3],
    'Protobuf': [mean_protobuf_get_all_users_pod_1, mean_protobuf_get_all_users_pod_2, mean_protobuf_get_all_users_pod_3]
})

###! GET USER BY ID BOTH JSON AND PROTOBUF

get_user_id_pod_dataframe = pd.DataFrame({
    'Report': ['Report 1', 'Report 2', 'Report 3'],
    'JSON': [mean_json_get_user_id_pod_1, mean_json_get_user_id_pod_2, mean_json_get_user_id_pod_3],
    'Protobuf': [mean_protobuf_get_user_id_pod_1, mean_protobuf_get_user_id_pod_2, mean_protobuf_get_user_id_pod_3]
})

###! GET ALL PRODUCT ENTITIES BOTH JSON AND PROTOBUF

get_all_product_entities_pod_dataframe = pd.DataFrame({
    'Report': ['Report 1', 'Report 2', 'Report 3'],
    'JSON': [mean_json_get_all_product_entities_pod_1, mean_json_get_all_product_entities_pod_2, mean_json_get_all_product_entities_pod_3],
    'Protobuf': [mean_protobuf_get_all_product_entities_pod_1, mean_protobuf_get_all_product_entities_pod_2, mean_protobuf_get_all_product_entities_pod_3]
})

###! TOTAL MEAN DATAFRAMES

### Normal Reporsts
total_create_users_dataframe = pd.DataFrame({
    'Report': ['Total Mean'],
    'JSON': [total_mean_json_create_user],
    'Protobuf': [total_mean_protobuf_create_user]
})

total_get_all_users_dataframe = pd.DataFrame({
    'Report': ['Total Mean'],
    'JSON': [total_mean_json_get_all_users],
    'Protobuf': [total_mean_protobuf_get_all_users]
})

total_get_user_id_dataframe = pd.DataFrame({
    'Report': ['Total Mean'],
    'JSON': [total_mean_json_get_user_id],
    'Protobuf': [total_mean_protobuf_get_user_id]
})

total_get_all_product_entities_dataframe = pd.DataFrame({
    'Report': ['Total Mean'],
    'JSON': [total_mean_json_get_all_product_entities],
    'Protobuf': [total_mean_protobuf_get_all_product_entities]
})

### POD Reports

total_create_users_pod_dataframe = pd.DataFrame({
    'Report': ['Total Mean'],
    'JSON': [total_mean_json_create_user_pod],
    'Protobuf': [total_mean_protobuf_create_user_pod]
})

total_get_all_users_pod_dataframe = pd.DataFrame({
    'Report': ['Total Mean'],
    'JSON': [total_mean_json_get_all_user_pod],
    'Protobuf': [total_mean_protobuf_get_all_user_pod]
})

total_get_user_id_pod_dataframe = pd.DataFrame({
    'Report': ['Total Mean'],
    'JSON': [total_mean_json_get_user_id_pod],
    'Protobuf': [total_mean_protobuf_get_user_id_pod]
})

total_get_all_product_entities_pod_dataframe = pd.DataFrame({
    'Report': ['Total Mean'],
    'JSON': [total_mean_json_get_all_product_entities_pod],
    'Protobuf': [total_mean_protobuf_get_all_product_entities_pod]
})


###! PLOT THE DATAFRAMES into histograms

###! CREATE USERS BOTH JSON AND PROTOBUF
create_users_dataframe.plot(x='Report', y=['JSON', 'Protobuf'], kind='bar')
plt.title("Create Users in terms of Response Time - JSON vs Protobuf")
plt.xlabel("Report")
plt.ylabel("Response Time(ms)")
plt.show()

###! GET ALL USERS BOTH JSON AND PROTOBUF
create_get_all_users_dataframe.plot(x='Report', y=['JSON', 'Protobuf'], kind='bar')
plt.title("Get All Users in terms of Response Time - JSON vs Protobuf")
plt.xlabel("Report")
plt.ylabel("Response Time(ms)")
plt.show()

###! GET USER BY ID BOTH JSON AND PROTOBUF
create_user_id_dataframe.plot(x='Report', y=['JSON', 'Protobuf'], kind='bar')
plt.title("Get User By ID in terms of Response Time - JSON vs Protobuf")
plt.xlabel("Report")
plt.ylabel("Response Time(ms)")
plt.show()

###! GET ALL PRODUCT ENTITIES BOTH JSON AND PROTOBUF
create_all_product_entities_dataframe.plot(x='Report', y=['JSON', 'Protobuf'], kind='bar')
plt.title("Get All Product Entities in terms of Response Time - JSON vs Protobuf")
plt.xlabel("Report")
plt.ylabel("Response Time(ms)")
plt.show()

##! PLOT POD DATAFRAMES into histograms

###! CREATE USERS BOTH JSON AND PROTOBUF
create_users_pod_dataframe.plot(x='Report', y=['JSON', 'Protobuf'], kind='bar')
plt.title("Create Users in terms of Energy Consumption - JSON vs Protobuf")
plt.xlabel("Report")
plt.ylabel("Energy Consumption(Watts)")
plt.show()

###! GET ALL USERS BOTH JSON AND PROTOBUF
get_all_users_pod_dataframe.plot(x='Report', y=['JSON', 'Protobuf'], kind='bar')
plt.title("Get All Users in terms of Energy Consumption - JSON vs Protobuf")
plt.xlabel("Report")
plt.ylabel("Energy Consumption(Watts)")

plt.show()

###! GET USER BY ID BOTH JSON AND PROTOBUF
get_user_id_pod_dataframe.plot(x='Report', y=['JSON', 'Protobuf'], kind='bar')
plt.title("Get User By ID in terms of Energy Consumption - JSON vs Protobuf")
plt.xlabel("Report")
plt.ylabel("Energy Consumption(Watts)")

plt.show()

###! GET ALL PRODUCT ENTITIES BOTH JSON AND PROTOBUF
get_all_product_entities_pod_dataframe.plot(x='Report', y=['JSON', 'Protobuf'], kind='bar')
plt.title("Get All Product Entities in terms of Energy Consumption - JSON vs Protobuf")
plt.xlabel("Report")
plt.ylabel("Energy Consumption(Watts)")

plt.show()

##! PLOT THE TOTAL MEAN COMPARISON

###! CREATE USERS BOTH JSON AND PROTOBUF
total_create_users_dataframe.plot(x='Report', y=['JSON', 'Protobuf'], kind='bar')
plt.title("Create Users in terms of Response Time - JSON vs Protobuf")
plt.xlabel("Report")
plt.ylabel("Response Time(ms)")
plt.show()

###! GET ALL USERS BOTH JSON AND PROTOBUF
total_get_all_users_dataframe.plot(x='Report', y=['JSON', 'Protobuf'], kind='bar')
plt.title("Get All Users in terms of Response Time - JSON vs Protobuf")
plt.xlabel("Report")
plt.ylabel("Response Time(ms)")
plt.show()

###! GET USER BY ID BOTH JSON AND PROTOBUF
total_get_user_id_dataframe.plot(x='Report', y=['JSON', 'Protobuf'], kind='bar')
plt.title("Get User By ID in terms of Response Time - JSON vs Protobuf")
plt.xlabel("Report")
plt.ylabel("Response Time(ms)")
plt.show()

###! GET ALL PRODUCT ENTITIES BOTH JSON AND PROTOBUF
total_get_all_product_entities_dataframe.plot(x='Report', y=['JSON', 'Protobuf'], kind='bar')
plt.title("Get All Product Entities in terms of Response Time - JSON vs Protobuf")
plt.xlabel("Report")
plt.ylabel("Response Time(ms)")
plt.show()

##! PLOT THE TOTAL MEAN COMPARISON

###! CREATE USERS BOTH JSON AND PROTOBUF
total_create_users_pod_dataframe.plot(x='Report', y=['JSON', 'Protobuf'], kind='bar')
plt.title("Create Users in terms of Energy Consumption - JSON vs Protobuf")
plt.xlabel("Report")
plt.ylabel("Energy Consumption(Watts)")
plt.show()

###! GET ALL USERS BOTH JSON AND PROTOBUF
total_get_all_users_pod_dataframe.plot(x='Report', y=['JSON', 'Protobuf'], kind='bar')
plt.title("Get All Users in terms of Energy Consumption - JSON vs Protobuf")
plt.xlabel("Report")
plt.ylabel("Energy Consumption(Watts)")
plt.show()

###! GET USER BY ID BOTH JSON AND PROTOBUF
total_get_user_id_pod_dataframe.plot(x='Report', y=['JSON', 'Protobuf'], kind='bar')
plt.title("Get User By ID in terms of Energy Consumption - JSON vs Protobuf")
plt.xlabel("Report")
plt.ylabel("Energy Consumption(Watts)")
plt.show()

###! GET ALL PRODUCT ENTITIES BOTH JSON AND PROTOBUF
total_get_all_product_entities_pod_dataframe.plot(x='Report', y=['JSON', 'Protobuf'], kind='bar')
plt.title("Get All Product Entities in terms of Energy Consumption - JSON vs Protobuf")
plt.xlabel("Report")
plt.ylabel("Energy Consumption(Watts)")
plt.show()

