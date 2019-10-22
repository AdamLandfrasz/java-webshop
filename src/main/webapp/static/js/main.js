import {RoutingManager} from './routing/manager.js';
import {ProductView} from './product/view.js';

function init(domReadyEvent) {
    const routingManager = new RoutingManager(window.history);
    const productView = new ProductView(routingManager);
    routingManager.goTo('/products/all');
}

window.addEventListener('DOMContentLoaded', init);
