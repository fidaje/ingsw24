import requests
from build_request.request_builder import add_unpacked, auth

name, is_fridge, quantity = add_unpacked()
credentials = auth()

url = "http://172.31.6.1:8080/ingsw24/gateway/6/foods/unpacked/"+name+"?isFridge="+is_fridge+"&quantity="+str(quantity)

payload = {}
headers = {
  'Authorization': 'Basic '+credentials
}

response = requests.request("PUT", url, headers=headers, data=payload)

print("Code: ", response.status_code)
