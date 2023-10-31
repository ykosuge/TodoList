# TODOアプリ 🤖

## 開発環境

- Android Studio
- Kotlin

## コーディング規約
コーディング規約とは、プログラムや情報システムを作る時に、プログラマたちが守るよう決められたルールのこと。

- [Kotlin Coding Conventions](https://kotlinlang.org/docs/reference/coding-conventions.html)
- [Project and code style guidelines](https://github.com/ribot/android-guidelines/blob/master/project_and_code_guidelines.md)

#### その他のルール

- apply禁止 (代わりにalsoを使う)
- run禁止 (代わりにletを使う)

参考: https://www.slideshare.net/RecruitLifestyle/kotlin-87339759

## 画面設計書
[技術研修資料](https://sonix-asia.atlassian.net/wiki/spaces/SDT/pages/372834343) から「TODOアプリ画面設計書.xlsx」をダウンロードしてください。

## 画面デザイン
[Figma](https://www.figma.com/file/ajoWa0Lxysp1qNYx9Y1sk9/TODO%E3%82%A2%E3%83%97%E3%83%AA?node-id=0%3A1) を参考にしてください。

#### レイアウト
最近の社内のandroidのプロジェクトでは [ConstraintLayout](https://qiita.com/nakker1218/items/0faa8c1ab504cc4cedea) を推奨しています。

#### コードの自動整形
1. Android Studio にSave Actionsプラグインをインストールします
2. Android Studio > Preferences > Other Settings > Save Actions を開き、以下の設定を有効にします。

- Activate save actions on save
- Activate save actions on shortcut
- Optimize imports
- Reformat file
- Rearrange field and methods
