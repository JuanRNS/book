import { ActivatedRouteSnapshot, CanActivate, GuardResult, MaybeAsync, Router, RouterStateSnapshot } from "@angular/router";
import { UserService } from "../../../features/services/user.service";
import { Injectable } from "@angular/core";

@Injectable({providedIn: 'root'})

export class AuthGuard implements CanActivate {

    constructor(private readonly auth: UserService, private readonly router: Router) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): MaybeAsync<GuardResult> {
        if(this.auth.isAuthenticated()) {
            return true;
        } else {
            this.router.navigate(['/login']);
            return false;
        }
    }
}