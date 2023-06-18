from serpapi import GoogleSearch
import json
import requests

def main():
  url = "https://underarmour.scene7.com/is/image/Underarmour/PS1345317-866_HF?rp=standard-0pad|pdpMainDesktop&scl=1&fmt=jpg&qlt=85&resMode=sharp2&cache=on,on&bgc=F0F0F0&wid=566&hei=708&size=566,708"
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

if __name__ == "__main__":
    print("Getting from API")
    main()