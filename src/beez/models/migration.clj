(ns beez.models.migration
    (:require [clojure.java.jdbc :as sql]
              [beez.models.beez :as beez]))
          

(defn migrated?
  "Have we migrated yet?"
  []
  (-> (sql/query beez/spec
                 [(str "select count(*) from information_schema.tables "
                       "where table_name='beez'")])
      first :count pos?))

(defn migrate
  "Migrate The DB"
  []
  (when (not (migrated?))
    (print "Creating Database Structure...") (flush)
    (sql/db-do-commands beez/spec
                        (sql/create-table-ddl
                          :beez
                          [[:id :serial "PRIMARY KEY"]
                          [:body :varchar "NOT NULL"]
                          [:created_at :timestamp
                            "NOT NULL" "DEFAULT CURRENT_TIMESTAMP"]]))
    (println " done")))