var caswen = {};
caswen.os = {};
caswen.os.isIOS = /iOS|iPhone|iPad|iPod/i.test(navigator.userAgent);
caswen.os.isAndroid = !caswen.os.isIOS;
caswen.callbacks = {}

caswen.callback = function (callbackname, response) {
   var callbackobject = caswen.callbacks[callbackname];
   console.log("xxxx"+callbackname);
   if (callbackobject !== undefined){
       if(callbackobject.callback != undefined){
          console.log("xxxxxx"+response);
            var ret = callbackobject.callback(response);
           if(ret === false){
               return
           }
           delete caswen.callbacks[callbackname];
       }
   }
}

caswen.takeNativeAction = function(commandname, parameters){
    console.log("caswen takenativeaction")
    var request = {};
    request.name = commandname;
    request.param = parameters;
    if(window.caswen.os.isAndroid){
        console.log("android take native action" + JSON.stringify(request));
        window.caswenwebview.takeNativeAction(JSON.stringify(request));
    } else {
        window.webkit.messageHandlers.caswenwebview.postMessage(JSON.stringify(request))
    }
}

caswen.takeNativeActionWithCallback = function(commandname, parameters, callback) {
    var callbackname = "nativetojs_callback_" +  (new Date()).getTime() + "_" + Math.floor(Math.random() * 10000);
    caswen.callbacks[callbackname] = {callback:callback};

    var request = {};
    request.name = commandname;
    request.param = parameters;
    request.param.callbackname = callbackname;
    if(window.caswen.os.isAndroid){
        window.caswenwebview.takeNativeAction(JSON.stringify(request));
    } else {
        window.webkit.messageHandlers.caswenwebview.postMessage(JSON.stringify(request))
    }
}

window.caswen = caswen;
