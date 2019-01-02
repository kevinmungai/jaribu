(ns io.wakamau.jaribu
  "this is the main file"
  (:gen-class) ; for -main method in uberjar
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]
            [io.pedestal.http.body-params :as body-params]
            [ring.util.response :as ring-resp]
            [io.wakamau.routes :as routes]
            [io.wakamau.database :as database]
            [com.stuartsierra.component :as component]
            [com.grzm.component.pedestal :as cp]))

(def service
  {:env :prod
   ::http/routes routes/routes
   ::http/resource-path "/public"
   ::http/type :jetty
   ::http/port 8080
   ::http/container-options {:h2c? true
                             :h2? false
                                        ;:keystore "test/hp/keystore.jks"
                                        ;:key-password "password"
                                        ;:ssl-port 8443
                             :ssl? false}})

(defn prod-pedestal
  []
  (println "\nCreating your [PRODUCTION] server...\n")
  (-> service
      http/default-interceptors
      ))


(def prod-config
  {:env :prod
   :host 999
   :port 999})

(defn prod-system
  "the production system"
  [{:keys [env host port]}]
  (component/system-map

   :database (database/new-database host port)

   :pedestal
   (component/using
    (cp/pedestal prod-pedestal)
    [:database])
   ))


(defn -main
  [& args]
  (println "\nCreating your [PRODUCTION] system...\n")
  (component/start (prod-system prod-config))
  )


