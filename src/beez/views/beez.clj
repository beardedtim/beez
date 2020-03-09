(ns beez.views.beez
    (:require [beez.views.layout :as layout]
              [hiccup.core :refer [h]]
              [hiccup.form :as form]
              [ring.util.anti-forgery :as anti-forgery]))
  
(defn beez-form
  "Our Form UI"
  []
  [:div {:id "beez-form" :class "sixteen columns alpha omega"}
    (form/form-to [:post "/"]
                  (anti-forgery/anti-forgery-field)
                  (form/label "beez" "What do you want to BEEZ?")
                  (form/text-area "beez")
                  (form/submit-button "BEEZ!"))])

(defn display-beez
  "How we view our beez"
  [beez]
  [:div {:class "beez sizteen columns alpha omega"}
    (map
      (fn [bee] [:h2 {:class "bee"} (h (:body bee))])
      beez)])
  
(defn index
    "Index View"
    [beez]
    (layout/common "BEEZ"
      (beez-form)
      [:div {:class "clear"}]
      (display-beez beez)))
