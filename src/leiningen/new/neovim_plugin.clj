(ns leiningen.new.neovim-plugin
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "neovim-plugin"))

(defn neovim-plugin
  "FIXME: write documentation"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' neovim-plugin project.")
    (->files data
             ["plugin/{{sanitized}}.vim" (render "plugin.vim" data)]
             ["src/{{sanitized}}/plugin.clj" (render "plugin.clj" data)]
             ["project.clj" (render "project.clj" data)]
             ["README.md" (render "README.md" data)])))
