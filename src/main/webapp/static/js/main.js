import {ProductView} from './product/view.js';

function init(domReadyEvent) {
    const routing = {setRoute: (newRoute) => history.replaceState({}, '', newRoute)};
    const productView = new ProductView(routing);
    productView.renderAll();
}

window.addEventListener('DOMContentLoaded', init);
