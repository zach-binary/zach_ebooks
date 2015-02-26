(defproject zach_ebooks "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [jarohen/chime "0.1.6"]
                 [twitter-api "0.7.8"]]
  :main ^:skip-aot zach-ebooks.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
