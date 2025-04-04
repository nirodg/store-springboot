// src/app/app.routes.ts

import { Routes } from '@angular/router';
export const routes: Routes = [
  {
    path: 'home',
    loadChildren: () =>
      import('./modules/home/home.routes').then((m) => m.homeRoutes),
    data: { title: 'Products' }
  },
  {
    path: 'cart',
    loadChildren: () =>
      import('./modules/home/cart/cart.routes').then((m) => m.cartRoutes),
    data: { title: 'Your Cart' }
  },
  {
    path: 'orders',
    loadChildren: () =>
      import('./modules/home/orders/orders.routes').then((m) => m.ordersRoutes),
    data: { title: 'Order History' }
  },
  {
    path: 'admin',
    loadChildren: () =>
      import('./modules/admin/admin.routes').then((m) => m.adminRoutes),
    data: { title: 'Admin Panel' }
  }
];
