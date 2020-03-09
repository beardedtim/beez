(ns beez.controllers.beez
    (:require [compojure.core :refer [defroutes GET POST]]
              [clojure.string :as str]
              [ring.util.response :as ring]
              [beez.views.beez :as view]
              [beez.models.beez :as model]))


(defn index []
  (view/index (model/all)))

(defn create
  [bee]
  (when-not (str/blank? bee)
    (model/create bee))
  (ring/redirect "/"))

(defroutes routes
  (GET "/" [] (index))
  (POST "/" [beez] (create beez)))