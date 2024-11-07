import {Component, inject, OnDestroy, OnInit, signal} from '@angular/core';
import {Router, RouterModule} from '@angular/router';
import {Subject} from 'rxjs';
import {takeUntil} from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import {AccountService} from 'app/core/auth/account.service';
import {Account} from 'app/core/auth/account.model';
import {CKEditorModule} from '@ckeditor/ckeditor5-angular';
import * as eee from 'ckeditor5';
// import { SlashCommand } from 'ckeditor5-premium-features';
// import * as ClassicEditorBuild from '@ckeditor/ckeditor5-build-classic';

@Component({
  standalone: true,
  selector: 'jhi-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
  imports: [SharedModule, RouterModule, CKEditorModule],
})
export default class HomeComponent implements OnInit, OnDestroy {
  title = 'angular';
  public Editor = eee.ClassicEditor;
  public config = {
    menuBar: {
      isVisible: true
    },
    toolbar: [
      'undo',
      'redo',
      '|',
      'previousPage',
      'nextPage',
      '|',
      'revisionHistory',
      'trackChanges',
      'comment',
      '|',
      'insertMergeField',
      'previewMergeFields',
      '|',
      'aiCommands',
      'aiAssistant',
      '|',
      'formatPainter',
      '|',
      'heading',
      '|',
      'fontSize',
      'fontFamily',
      'fontColor',
      'fontBackgroundColor',
      '|',
      'bold',
      'italic',
      'underline',
      '|',
      'link',
      'insertImage',
      'insertTable',
      '|',
      'alignment',
      '|',
      'bulletedList',
      'numberedList',
      'multiLevelList',
      'todoList',
      'outdent',
      'indent',
    ],
    plugins: [
      eee.AccessibilityHelp,
      eee.Alignment,
      eee.Autoformat,
      eee.AutoImage,
      eee.AutoLink,
      eee.Autosave,
      eee.BalloonToolbar,
      eee.Bold,
      eee.CKBox,
      eee.CKBoxImageEdit,
      eee.CloudServices,
      eee.Code,
      eee.Essentials,
      eee.FindAndReplace,
      eee.FontBackgroundColor,
      eee.FontColor,
      eee.FontFamily,
      eee.FontSize,
      eee.Heading,
      eee.HorizontalLine,
      eee.ImageBlock,
      eee.ImageCaption,
      eee.ImageInline,
      eee.ImageInsert,
      eee.ImageInsertViaUrl,
      eee.ImageResize,
      eee.ImageStyle,
      eee.ImageTextAlternative,
      eee.ImageToolbar,
      eee.ImageUpload,
      eee.Indent,
      eee.IndentBlock,
      eee.Italic,
      eee.Link,
      eee.LinkImage,
      eee.List,
      eee.ListProperties,
      eee.Mention,
      eee.PageBreak,
      eee.Paragraph,
      eee.PasteFromOffice,
      eee.PictureEditing,
      eee.RemoveFormat,
      eee.SelectAll,
      eee.SpecialCharacters,
      eee.SpecialCharactersArrows,
      eee.SpecialCharactersCurrency,
      eee.SpecialCharactersEssentials,
      eee.SpecialCharactersLatin,
      eee.SpecialCharactersMathematical,
      eee.SpecialCharactersText,
      eee.Strikethrough,
      eee.Subscript,
      eee.Superscript,
      eee.Table,
      eee.TableCaption,
      eee.TableCellProperties,
      eee.TableColumnResize,
      eee.TableProperties,
      eee.TableToolbar,
      eee.TextTransformation,
      eee.TodoList,
      eee.Underline,
      eee.Undo,
    ],
    // licenseKey: '<YOUR_LICENSE_KEY>',
    // mention: {
    //     Mention configuration
    // }
  };

  account = signal<Account | null>(null);

  private readonly destroy$ = new Subject<void>();

  private readonly accountService = inject(AccountService);
  private readonly router = inject(Router);

  ngOnInit(): void {
    this.accountService
      .getAuthenticationState()
      .pipe(takeUntil(this.destroy$))
      .subscribe(account => this.account.set(account));
  }

  login(): void {
    this.router.navigate(['/login']);
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
