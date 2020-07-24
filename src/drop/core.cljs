(ns drop.core
    (:require
      [reagent.core :as r]
      [reagent.dom :as d]))

;; -------------------------
;; Database
(def db
  (r/atom
   {:templates [:div]
    :customs [:div]}))

;; -------------------------
;; Functions
(defn on-drag-end
  [e]
  (let [idx (int (.getAttribute (-> e .-currentTarget) "idx"))
        el (nth (:templates @db) idx)]
    (swap! db update :customs conj el)))

;; ------------------------
;; Init 
(swap! db update :templates conj [:input {:type "text" :idx 1 :draggable true :on-drag-end #(on-drag-end %) :default-value "Text Input"}])

;; -------------------------
;; Views
(defn templates []
  [:div.templates (:templates @db)])

(defn customs []
  [:div.customs (:customs @db)])

(defn home-page []
  [:div
   [templates]
   [customs]])

;; -------------------------
;; Initialize app

(defn mount-root []
  (d/render [home-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
