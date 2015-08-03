(defproject jutland "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [
    [org.clojure/clojure "1.6.0"]
    [org.clojure/tools.cli "0.2.4"]
    [com.datomic/datomic-free "0.9.5206" :exclusions [joda-time]]
  ]
  :datomic {:schemas ["resources" ["schema.edn"]]}
  :main ^:skip-aot jutland.core
  :target-path "target/%s"
  :profiles {
    :uberjar {:aot :all}
    :dev {
      :datomic {
        :config "resources/transactor.properties"
        :db-uri "datomic:free://127.0.0.1:4334/jutland"
      }
    }
  }
)
