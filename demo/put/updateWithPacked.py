import requests
from build_request.request_builder import add_packed, auth

code, is_fridge, quantity, expiration_date = add_packed()
credentials = auth()

url = "http://172.31.6.1:8080/ingsw24/gateway/6/foods/packed/"+code+"?isFridge="+is_fridge+"&quantity="+str(quantity)+"&expirationDate="+expiration_date

payload = {}
headers = {
  'Authorization': 'Basic '+credentials
}

response = requests.request("PUT", url, headers=headers, data=payload)

print(response.status_code)
