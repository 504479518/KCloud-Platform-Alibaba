export default (initialState: API.getByUuidV3Params) => {
	// 在这里按照初始化数据定义项目中的权限，统一管理
	// 参考文档 https://umijs.org/docs/max/access
	const canSeeAdmin = (
		initialState && initialState.uuid !== 'dontHaveAccess'
	);
	return {
		canSeeAdmin,
	};
};
