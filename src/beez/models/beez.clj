(ns beez.models.beez
  (:require [clojure.java.jdbc :as sql]))

(def spec (or (System/getenv "DATABASE_URL")
              "postgresql://username:password@localhost:9098/database"))

(defn all []
  (into [] (sql/query spec ["select * from beez order by id desc"])))

(defn create [bee]
  (sql/insert! spec :beez [:body] [bee]))
