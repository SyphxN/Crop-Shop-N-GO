from serpapi import GoogleSearch
import json
import requests

with open("src\\main\\java\\url.txt", "r") as file:
    url = file.read().strip()
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
