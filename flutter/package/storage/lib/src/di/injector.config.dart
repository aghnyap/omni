// GENERATED CODE - DO NOT MODIFY BY HAND

// **************************************************************************
// InjectableConfigGenerator
// **************************************************************************

// ignore_for_file: type=lint
// coverage:ignore-file

// ignore_for_file: no_leading_underscores_for_library_prefixes
import 'package:get_it/get_it.dart' as _i1;
import 'package:injectable/injectable.dart' as _i2;
import 'package:messenger/messenger.dart' as _i3;
import 'package:shared_preferences/shared_preferences.dart' as _i4;
import 'package:storage/src/di/register_module.dart' as _i7;
import 'package:storage/src/storage/local_storage.dart' as _i5;
import 'package:storage/src/storage/shared_preferences_local_storage.dart'
    as _i6;

extension GetItInjectableX on _i1.GetIt {
// initializes the registration of main-scope dependencies inside of GetIt
  Future<_i1.GetIt> init({
    String? environment,
    _i2.EnvironmentFilter? environmentFilter,
  }) async {
    final gh = _i2.GetItHelper(
      this,
      environment,
      environmentFilter,
    );
    final registerModule = _$RegisterModule();
    gh.factory<_i3.MessengerBus>(
      () => registerModule.messengerBus,
      instanceName: 'storage',
    );
    await gh.factoryAsync<_i4.SharedPreferences>(
      () => registerModule.prefs,
      preResolve: true,
    );
    gh.singleton<_i5.LocalStorage>(_i6.SharedPreferencesLocalStorage(
      gh<_i3.MessengerBus>(instanceName: 'storage'),
      gh<_i4.SharedPreferences>(),
    ));
    return this;
  }
}

class _$RegisterModule extends _i7.RegisterModule {}
