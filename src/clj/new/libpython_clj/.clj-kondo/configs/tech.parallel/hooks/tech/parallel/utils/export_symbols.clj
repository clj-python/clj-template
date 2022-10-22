(ns hooks.tech.parallel.utils.export-symbols
  "The export-symbols macro from tech/parallel/utils.clj"
  (:require [clj-kondo.hooks-api :as api]))

;; from: tech/parallel/utils.clj

;; (defmacro export-symbols
;;   [src-ns & symbol-list]
;;   `(do
;;      (require '~src-ns)
;;      ~@(->> symbol-list
;;             (mapv
;;              (fn [sym-name]
;;                `(let [varval# (requiring-resolve (symbol ~(name src-ns)
;;                                                          ~(name sym-name)))
;;                       var-meta# (meta varval#)]
;;                   (when (:macro var-meta#)
;;                     (throw
;;                      (ex-info
;;                       (format "Cannot export macros as this breaks aot: %s"
;;                               '~sym-name)
;;                       {:symbol '~sym-name})))
;;                   (def ~(symbol (name sym-name)) @varval#)
;;                   (alter-meta! #'~(symbol (name sym-name))
;;                                merge
;;                                (select-keys var-meta#
;;                                             [:file :line :column
;;                                              :doc
;;                                              :column :tag
;;                                              :arglists]))))))))

(defn export-symbols
  "Macro in tech/parallel/utils.clj.

  Example call:

    (export-symbols libpython-clj.jna.base
                    find-pylib-symbol
                    as-pyobj
                    ensure-pyobj)

  This has the form:

    (export-symbols ns-name var-name-1 var-name-2 ... var-name-n)

  May be treating it as:

    (do
      (def var-name-1 ns-name/var-name-1)
      (def var-name-2 ns-name/var-name-2)
      ,,,
      (def var-name-n ns-name/var-name-n))

  is acceptable.

  Thanks lread :)
  
  "
  [{:keys [:node]}]
  (let [[_ ns-node & var-nodes] (:children node)
        new-node (api/list-node
                  (conj
                   (doall
                    (for [var-node var-nodes
                          :let [var-sym (api/sexpr var-node)
                                var-fq (str (api/sexpr ns-node) "/" var-sym)]]
                      (with-meta
                        (api/list-node
                         [(api/token-node 'def)
                          (api/token-node (symbol var-sym))
                          (api/token-node (symbol var-fq))])
                        (meta var-node))))
                   (api/token-node 'do)))]
    ;; XXX: uncomment following and run clj-kondo on cl_format.clj to debug
    ;;(prn (api/sexpr node))
    ;;(prn (api/sexpr new-node))
    {:node new-node}))
