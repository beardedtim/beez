(ns beez.views.layout
  (:require [hiccup.page :as h]))

(defn common
  "Common Layout"
  [title & body]
  (h/html5
    [:head
      [:meta {:charset "utf-8"}]
      [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge,chrome-1"}]
      [:meta {:name "viewport" :content "width=device-width, intial-scale=1, maxium-scale-=1"}]
      [:title title]
      (h/include-css "/stylesheets/base.css"
                    "/stylesheets/skeleton.css"
                    "/stylesheets/screen.css")
      (h/include-css "http://fonts.googleapis.com/css?family=Sigmar+One&v1")]
    [:body
      [:div {:id "header"}
        [:h1 {:class "container" } "BEEZ!"]]
      [:div {:id "content" :class "container" } body]]))

(defn four-oh-four
  "Our Not Found Layout"
  []
  (common "Page Not Found"
          [:div {:id "four-oh-four"}
            "The page tyou requested was not found!"]))