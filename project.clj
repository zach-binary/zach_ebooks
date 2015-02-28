(defproject zach_ebooks "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [twitter-api "0.7.8"]
                 [org.slf4j/slf4j-api "1.6.2"]
                 [org.slf4j/slf4j-simple "1.6.2"]]
  :main ^:skip-aot zach-ebooks.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot [zach-ebooks.core]}})
