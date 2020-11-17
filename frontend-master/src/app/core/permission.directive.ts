import {
  Directive,
  EmbeddedViewRef,
  Input,
  OnChanges,
  OnDestroy,
  OnInit,
  SimpleChanges,
  TemplateRef,
  ViewContainerRef
} from '@angular/core';
import {Role} from './auth/role.model';
import {
  combineLatest,
  ReplaySubject,
  Subscription
} from 'rxjs';
import {CurrentUserService} from './auth/current-user.service';

@Directive({
  selector: '[appPermission]'
})
export class PermissionDirective
  implements OnInit, OnChanges, OnDestroy {

  @Input() appPermission!: Role;

  private role$ = new ReplaySubject<Role>(1);
  private sub = Subscription.EMPTY;
  private view?: EmbeddedViewRef<void>;

  constructor(
    private readonly template: TemplateRef<void>,
    private vcr: ViewContainerRef,
    private currentUserService: CurrentUserService
  ) {
  }

  ngOnInit(): void {
   this.refreshButton()
  }
  //TODO: sometimes it takes a page refresh to show button
  refreshButton():void{
    this.sub = combineLatest([
      this.role$,
      this.currentUserService.user$
    ]).subscribe(([role, user]) => {
      const mustShow = user.hasRole(role);

      if (mustShow) {
        if (this.view == undefined) {
          this.view = this.vcr.createEmbeddedView(
            this.template
          );
        }
      } else {
        if (this.view != undefined) {
          this.view.destroy();
        }
      }
    });
  }


  ngOnChanges(changes: SimpleChanges): void {
    this.refreshButton();
    if (changes.appPermission == undefined) {
      return;
    }
    const role = changes.appPermission
      .currentValue as Role;
    this.role$.next(role);
  }


  ngOnDestroy(): void {
    this.sub.unsubscribe();
    this.view?.destroy();
    this.vcr.clear();
  }

}
