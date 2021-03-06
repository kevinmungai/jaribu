(ns dev
  "Tools for interactive development with the REPL. This file should
  not be included in a production build of the application.

  Call `(reset)` to reload modified code and (re)start the system.

  The system under development is `system`, referred from
  `com.stuartsierra.component.repl/system`.

  See also https://github.com/stuartsierra/component.repl"
  (:require
   [clojure.java.io :as io]
   [clojure.java.javadoc :refer [javadoc]]
   [clojure.pprint :refer [pprint]]
   [clojure.reflect :refer [reflect]]
   [clojure.repl :refer [apropos dir doc find-doc pst source]]
   [clojure.set :as set]
   [clojure.string :as string]
   [clojure.test :as test]
   [clojure.tools.namespace.repl :refer [refresh refresh-all clear]]
   [com.stuartsierra.component :as component]
   [com.stuartsierra.component.repl :refer [reset set-init start stop system]]
   [io.wakamau.jaribu :as jaribu]
   [io.wakamau.database :as database]
   [io.wakamau.routes :as routes]
   [io.pedestal.http :as http]
   [com.grzm.component.pedestal :as cp]
   [io.pedestal.http.route :as route]))

;; Do not try to load source code from 'resources' directory
(clojure.tools.namespace.repl/set-refresh-dirs "dev" "src" "test")

(defn new-pedestal
  []
  (println "\nstarting your [DEVELOPMENT] server...\n")
  (-> jaribu/service
      (merge {:env :dev
              ;; ::http/routes #(route/expand-routes (deref #'routes/routes))
              ;; ::http/type :jetty
              ::http/join? false})
      http/default-interceptors
      http/dev-interceptors
      ))

(defn dev-system
  "Constructs a system map suitable for interactive development."
  [env host port]
  (component/system-map

   :database (database/new-database host port)

   :pedestal
   (component/using
    (cp/pedestal new-pedestal)
    [:database])
   ))

(set-init (fn [_] (dev-system :dev 999 999)))
