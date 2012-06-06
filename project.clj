(defproject filtering "0.1.0-SNAPSHOT"
  :min-lein-version "2.0.0"
  :description "Filter particular database tables with multiple \"orthogonal\" criterias in AND"
  :url "https://github.com/manuelp/elekti"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :plugins [[lein-swank "1.4.4"]
            [lein-marginalia "0.7.0"]]
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [joda-time/joda-time "2.1"]])