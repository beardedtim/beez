(ns beez.core
  (:require [compojure.core :refer [defroutes]]
            [ring.adapter.jetty :as ring]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [beez.controllers.beez :as beez]
            [beez.views.layout :as layout]
            [beez.models.migration :as schema])
  (:gen-class))

(def public-path (or (System/getenv "PUBLIC_PATH") "/public/"))
(def public-port (or (System/getenv "PORT") "8080"))

(defn get-port
  "Returns the port as an Int"
  []
  (Integer. public-port))

(defroutes routes
  beez/routes
  (route/resources public-path)
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
  (let [port (get-port)]
    (start port)))