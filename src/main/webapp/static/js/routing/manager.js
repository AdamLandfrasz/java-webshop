export function RoutingManager(history) {
    this.history = history;
    this.matchers = {};
}

RoutingManager.prototype.install = function(matcher, handler) {
    let key = matcher;
    if (!(matcher instanceof RegExp)) {
        key = new RegExp(matcher);
    }

    // TODO: handle duplicates
    this.matchers[key] = handler;
}

RoutingManager.prototype.setRoute = function(newRoute) {
    this.history.replaceState({}, '', newRoute);
}
