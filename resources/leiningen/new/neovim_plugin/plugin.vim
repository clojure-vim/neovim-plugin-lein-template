let s:p_dir = expand('<sfile>:p:h')
let g:{{sanitized}}_is_running = 0
let g:{{sanitized}}_rpc_channel = 0

function! s:start()
    if g:{{sanitized}}_is_running == 0
        echo 'starting {{name}}...'
        let g:{{sanitized}}_is_running = 1
        let jar_file_path = s:p_dir . '/../' . '{{name}}-plugin-0.1.0-standalone.jar'
        let g:{{sanitized}}_rpc_channel = call jobstart(['java', '-jar', jar_file_path], {'rpc': v:true})
    endif
endfunction

" Rename functions and add your messages
function! PluginAction()
    call s:start()
    let res = rpcnotify(g:{{sanitized}}_rpc_channel, '<message>', [])
    return res
endfunction

