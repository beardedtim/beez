(defproject beez "0.0.0-SNAPSHOT"
  :description "Workers Written In Clojure"
  :url "https://github.com/beardedtim/beez"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/java.jdbc "0.6.1"]
                 [org.postgresql/postgresql "9.4-1201-jdbc41"]
                 [ring/ring-jetty-adapter "1.4.0"]
                 [compojure "1.4.0"]
                 [ring/ring-defaults "0.1.2"]
                 [hiccup "1.0.5"]]
  :main ^:skip-aot beez.core
  :uberjar-name "beez-standalone.jar"
  :plugins [[lein-ring "0.8.13"]]
  :ring {:handler beez.core/application
         :init beez.models.migration/migrate}
  :target-path "target/%s"
  :profiles {:dev {:dependenices [[javax.servlet/servlet-api "2.5"]
                                  [ring-mock "0.1.5"]]}
             :uberjar {:aot :all}})
