from serpapi import GoogleSearch
import json
import requests
from imgurpython import ImgurClient

def upload_image_to_imgur(image_path, client_id, client_secret):
    client = ImgurClient(client_id, client_secret)
    response = client.upload_from_path(image_path, anon=True)
    return response['link']

def get_image_https_url(image_path, client_id, client_secret):
    image_url = upload_image_to_imgur(image_path, client_id, client_secret)
    response = requests.get(image_url)
    return response.url

image_path = "Pictures\\image.jpg"
client_id = '99af53b8d23f490'
client_secret = '40320906edb92b3e38ee7f5a2843362b12607ec2'
https_url = get_image_https_url(image_path, client_id, client_secret)
print("HTTPS URL:", https_url)

with open("src\\main\\java\\url.txt", "r") as file:
    url = https_url
params = {
  "api_key": "eeb332563bfc822f9412cc8db88139c113f2ea9b05ab11dc38a7028c2ebf52a6",
  "engine": "google_lens",
  "url": url
}

search = GoogleSearch(params)
results = search.get_dict()
with open(r'src\\main\\java\\serpAPI.json','w') as f:
    json.dump(results,f,indent = 4)
#print(results['visual_matches'])
