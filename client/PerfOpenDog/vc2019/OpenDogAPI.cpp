//#include "OpenDogAPI.h"
//
//
//size_t receiveData(void* contents, size_t size, size_t nmemb, void* stream)
//{
//	std::string* str = (std::string*)stream;
//	(*str).append((char*)contents, size * nmemb);
//	return size * nmemb;
//}
//
//int loginAPI(string username, string password)
//{
//
//	string url = baseUrl + "user/login";
//	std::string response;
//
//	CURL* curl;
//	CURLcode res;
//	nlohmann::json body;
//	// 设置请求地址
//	curl_easy_setopt(curl, CURLOPT_URL, url.c_str());
//
//	// 设置请求头信息
//	curl_slist* pList = nullptr;
//	pList = curl_slist_append(pList, "Content-Type:application/json");
//	curl_easy_setopt(curl, CURLOPT_HTTPHEADER, pList);
//	//设置问非0表示本次操作为post
//	curl_easy_setopt(curl, CURLOPT_POST, 1);
//	//设置请求体
//	body["username"] = username.c_str();
//	body["password"] = password.c_str();
//	curl_easy_setopt(curl, CURLOPT_POSTFIELDS, body.dump().c_str());
//	// 设置接收函数
//	curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, receiveData);
//	// 设置接收内容
//	curl_easy_setopt(curl, CURLOPT_WRITEDATA, (void*)&response);
//
//	// 执行并检查返回值
//	res = curl_easy_perform(curl);
//	if (res != CURLE_OK)
//	{
//		switch (res)
//		{
//		case CURLE_UNSUPPORTED_PROTOCOL:
//			fprintf(stderr, "不支持的协议,由URL的头部指定\n");
//		case CURLE_COULDNT_CONNECT:
//			fprintf(stderr, "不能连接到remote主机或者代理\n");
//		case CURLE_HTTP_RETURNED_ERROR:
//			fprintf(stderr, "http返回错误\n");
//		case CURLE_READ_ERROR:
//			fprintf(stderr, "读本地文件错误\n");
//		default:
//			fprintf(stderr, "返回值:%d\n", res);
//		}
//
//
//		return -1;
//	}
//	nlohmann::json j = nlohmann::json::parse(response);
//
//	std::cout << j["state"] << std::endl;
//
//	curl_easy_cleanup(curl);
//	curl_global_cleanup();
//	return 0;
//}
//
//
