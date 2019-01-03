(ns io.wakamau.database
  (:require [com.stuartsierra.component :as component]))

(defrecord Database [host port connection]

  component/Lifecycle

  (start [component]
    (println "\nStarting Database...\n")
    (let [connection (atom {:name "mungai"})]
      (assoc component :connection connection)))

  (stop [component]
    (println "\nStopping Database...\n")
    component))

(defn new-database
  [host port]
  (map->Database {:host host :port port}))
