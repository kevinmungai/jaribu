(ns io.wakamau.database
  (:require [com.stuartsierra.component :as component]))

(defrecord Database [host port connection]

  component/Lifecycle

  (start [component]
    (println ";; Starting Database")
    (let [connection (atom {})]
      (assoc component :connection connection)))

  (stop [component]
    (println ";; Stoppping Database")
    (swap! (:connection component)
           empty)))

(defn new-database
  [host port]
  (map->Database {:host host :port port}))
