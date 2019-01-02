(ns io.wakamau.routes
  (:require [com.grzm.component.pedestal :as cp]))

(defn respond-hello [request]
  {:status 200 :body "Hello, world!"})

(defn show-db
  [request]
  (let [database (cp/use-component request :database)]
    {:status 200
     :body (str @(:connection database))}))

(def show-db-interceptor
  {:name :show-db-interceptor
   :enter (fn [context] context)})

(def routes
  #{["/greet" :get respond-hello :route-name :greet]
    ["/db" :get [(cp/using-component :database) `show-db]]})
