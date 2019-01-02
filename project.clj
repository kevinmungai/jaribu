(defproject io.wakamau/jaribu "0.1.0-SNAPSHOT"
  :description "trying out pedestal and component"
  :url "https://github.com/kevinmungai/jaribu"
  :license {:name "TODO: Choose a license"
            :url "http://choosealicense.com/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [com.stuartsierra/component "0.3.2"]
                 [io.pedestal/pedestal.service       "0.5.5"]
                 [io.pedestal/pedestal.service-tools "0.5.5"] ;; Only needed for ns-watching; WAR tooling
                 [io.pedestal/pedestal.jetty         "0.5.5"]
                 [ch.qos.logback/logback-classic "1.2.3" :exclusions [org.slf4j/slf4j-api]]
                 [org.slf4j/jul-to-slf4j "1.7.25"]
                 [org.slf4j/jcl-over-slf4j "1.7.25"]
                 [org.slf4j/log4j-over-slf4j "1.7.25"]
                 [io.pedestal/pedestal.interceptor "0.5.5"]
                 [io.pedestal/pedestal.log "0.5.5"]
                 [io.pedestal/pedestal.route "0.5.5"]
                 
                 [com.grzm/component.pedestal "0.1.7"];; pedestal component wrapper
                 ]
  :profiles {:dev {:dependencies [[org.clojure/tools.namespace "0.2.11"]
                                  [com.stuartsierra/component.repl "0.2.0"]
                                  [io.pedestal/pedestal.service-tools "0.5.5"]]
                   :source-paths ["dev"]
                   }
             ;; :uberjar {:aot [io.wakamau.jaribu]}
             }
  ;; :main ^{:skip-aot true} io.wakamau.jaribu
  )
