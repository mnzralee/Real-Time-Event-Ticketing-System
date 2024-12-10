import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { ConfigurationComponent } from './components/configuration/configuration.component';
import { VendorsComponent } from './components/vendors/vendors.component';
import { CustomersComponent } from './components/customers/customers.component';
import { AnalyticsComponent } from './components/analytics/analytics.component';
import { NgModule } from '@angular/core';

export const routes: Routes = [
    { path: 'home', component: HomeComponent },
    { path: 'configuration', component: ConfigurationComponent },
    { path: 'vendor', component: VendorsComponent },
    { path: 'customer', component: CustomersComponent },
    { path: 'analytics', component: AnalyticsComponent },
    { path: '', redirectTo: '/home', pathMatch: 'full' }, // Default route
    { path: '**', redirectTo: '/home' } // Wildcard route fir invalid URLs
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule],
})

export class AppRoutingModule {};