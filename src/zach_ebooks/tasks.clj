(ns zach-ebooks.tasks
  	(:use 
	  	[twitter.oauth]
	  	[twitter.callbacks]
	   	[twitter.callbacks.handlers]
	  	[twitter.api.restful])
	(:require [zach-ebooks.config :as config]))
	

(def my-creds (make-oauth-creds config/consumerKey
								config/consumerSecret
								config/accessToken
								config/accessTokenSecret))


(defn fit-to-tweet
	"Ensures the text is 140 characters"
	[status]
	(if (> (.length status) 140)
		(subs status 0 140)
		(str status)))

(defn update-status
"Send a status update with the following text"
	[text]
	(let [status (fit-to-tweet text)]
		(statuses-update :oauth-creds my-creds
					 :params {:status status})))

