(ns io.wakamau.database
  (:require [com.stuartsierra.component :as component]))

(defrecord Database [host port connection]

  component/Lifecycle

  (start [component]
    (println ";; Starting Database")
    (let [connection (atom {:name "kevin"})]
      (assoc component :connection connection)))

  (stop [component]
    (println ";; Stoppping Database")
    component))

(defn new-database
  [host port]
  (map->Database {:host host :port port}))
