export function RoutingManager(history) {
    this.history = history;
    this.matchers = new Map();
}

RoutingManager.prototype.install = function(matcher, handler) {
    let key = matcher;
    if (!(matcher instanceof RegExp)) {
        key = new RegExp(matcher);
    }

    // TODO: handle duplicates
    this.matchers.set(key, handler);
}

RoutingManager.prototype.setRoute = function(newRoute) {
    this.history.replaceState({}, '', newRoute);
}

RoutingManager.prototype.goTo = function(url) {
    for (const [matcher, handler] of this.matchers) {
        if(url.match(matcher)) {
            handler();
            return;
        }
    }
    console.log(`no mather found for ${url}`);
}
