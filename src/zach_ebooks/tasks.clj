(ns zach-ebooks.tasks
  	(:use 
	  	[twitter.oauth]
	  	[twitter.callbacks]
	   	[twitter.callbacks.handlers]
	  	[twitter.api.restful])
	(:require [zach-ebooks.config :as config]))
	

(def quotes ["Hi test" "something funny!"])

(def my-creds (make-oauth-creds config/consumerKey
								config/consumerSecret
								config/userToken
								config/userTokenSecret))


(defn update-status
"Send a status update with the following text"
	[text]
	(statuses-update :oauth-creds my-creds
					 :params {:status text}))

