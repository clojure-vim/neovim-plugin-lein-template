(ns {{name}}.plugin
  (:require
    [neovim-client.1.api :as api]
    [neovim-client.nvim :as nvim])
  (:gen-class))

(defn my-rpc [msg]
  (api/command-async nvim ":echo 'Hello neovim'" (fn [_] nil)))

(def rpc-methods
  {:my-rpc my-rpc})

(defn -main [& args]
  (map (fn [[method-name method-fn]]
         (nvim/register-method! nvim (name method-name) method-fn))
       rpc-methods)

  (while (not= (api/get-var nvim "{{sanitized}}_is_running") 0)
    (Thread/sleep 5000)))

