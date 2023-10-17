// GENERATED CODE - DO NOT MODIFY BY HAND

// **************************************************************************
// InjectableConfigGenerator
// **************************************************************************

// ignore_for_file: type=lint
// coverage:ignore-file

// ignore_for_file: no_leading_underscores_for_library_prefixes
import 'package:counter/src/counter/counter_cubit.dart' as _i4;
import 'package:counter/src/di/register_module.dart' as _i5;
import 'package:get_it/get_it.dart' as _i1;
import 'package:injectable/injectable.dart' as _i2;
import 'package:messenger/messenger.dart' as _i3;

extension GetItInjectableX on _i1.GetIt {
// initializes the registration of main-scope dependencies inside of GetIt
  _i1.GetIt init({
    String? environment,
    _i2.EnvironmentFilter? environmentFilter,
  }) {
    final gh = _i2.GetItHelper(
      this,
      environment,
      environmentFilter,
    );
    final registerModule = _$RegisterModule();
    gh.factory<_i3.MessengerBus>(
      () => registerModule.messengerBus,
      instanceName: 'counter',
    );
    gh.factory<_i4.CounterCubit>(
        () => _i4.CounterCubit(gh<_i3.MessengerBus>(instanceName: 'counter')));
    return this;
  }
}

class _$RegisterModule extends _i5.RegisterModule {}
