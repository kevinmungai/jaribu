(ns io.wakamau.pedestal
  (:require [io.pedestal.http :as http]
            [com.stuartsierra.component :as component]
            ))

(defn init-db [database]
  (def database-interceptor
    "creating a database interceptor with the current database connection"
    {:name :database-interceptor
     :enter (fn [context] (assoc context :database database))
     }))

(defn test? [service-map]
  (= :test (:env service-map)))

(defrecord Pedestal
    [service-map service database]

  component/Lifecycle

  (start [this]
    (println ";; Starting Pedestal")
    (println (str ";; Database Component:\n\n" database))
    (init-db database)
    (if service
      this
      (cond-> service-map
        true http/create-server
        (not (test? service-map)) http/start
        true ((partial assoc this :service)))
      ))

  (stop [this]
    (println ";; Stopping Pedestal")
    (when (and service (not (test? service-map)))
      (http/stop service))
    (assoc this service nil)))

(defn new-pedestal
  []
  (map->Pedestal {}))
