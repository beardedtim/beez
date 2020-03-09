(ns beez.core
  (:require [compojure.core :refer [defroutes]]
            [ring.adapter.jetty :as ring]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [beez.controllers.beez :as beez]
            [beez.views.layout :as layout]
            [beez.models.migration :as schema])
  (:gen-class))

(defroutes routes
  beez/routes
  (route/resources "/")
  (route/not-found (layout/four-oh-four)))

(def application (wrap-defaults routes site-defaults))

(defn start
  "Start the application"
  [port]
  (ring/run-jetty application {:port port
                               :join? false}))

(defn -main
  "Our entrypoint"
  []
  (schema/migrate)
  (let [port (Integer. (or (System/getenv "PORT") "8080"))]
    (start port)))