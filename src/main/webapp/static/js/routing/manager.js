export function RoutingManager(history) {
    this.history = history;
}

RoutingManager.prototype.install = function(matcher, handler) {
    // TODO: register handlers
}

RoutingManager.prototype.setRoute = function(newRoute) {
    this.history.replaceState({}, '', newRoute);
}
