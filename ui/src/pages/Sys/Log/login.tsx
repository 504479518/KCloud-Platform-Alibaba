import type {ProColumns} from '@ant-design/pro-components';
import {ProTable} from '@ant-design/pro-components';
import {pageV3} from "@/services/admin/loginLog";
import {Button} from "antd";
import {DeleteOutlined, ExportOutlined} from "@ant-design/icons";
import {trim} from "@/utils/format";
import {Excel, ExportToExcel} from "@/utils/export";

export default () => {

	const statusEnum = {
		0: '0',
		1: '1'
	};

	const typeEnum = {
		'password': 'password',
		'mobile': 'mobile',
		'mail': 'mail',
		'authorization_code': 'authorization_code'
	};

	type TableColumns = {
		id: number | undefined;
		username: string | undefined;
		ip: string | undefined;
		address: string | undefined;
		browser: string | undefined;
		os: string | undefined;
		status: string | undefined;
		errorMessage: string | undefined;
		type: string | undefined;
		createTime: string | undefined;
	};

	let loginLogList: TableColumns[]

	const getPageQuery = (params: any) => {
		return {
			pageSize: params?.pageSize,
			pageNum: params?.current,
			username: trim(params?.username),
			ip: trim(params?.ip),
			address: trim(params?.address),
			browser: trim(params?.browser),
			status: params?.status,
			os: trim(params?.os),
			type: params?.type,
			errorMessage: trim(params?.errorMessage),
			params: {
				startDate: params?.startDate,
				endDate: params?.endDate
			}
		};
	}

	const getStatusDesc = (status: string | undefined) => {
		if (status === "0") {
			return "登录成功"
		} else {
			return "登录失败"
		}
	}

	const getTypeDesc = (type: string | undefined) => {
		if (type === "password") {
			return "用户名密码登录";
		} else if (type === "mail") {
			return "邮箱登录"
		} else if (type === "mobile") {
			return "手机号登录";
		} else if (type === "authorization_code") {
			return "授权码登录";
		}
	}

	const exportToExcel = async () => {
		let params: Excel
		const list: TableColumns[] = [];
		// 格式化数据
		loginLogList.forEach(item => {
			item.status = getStatusDesc(item.status)
			item.type = getTypeDesc(item.type)
			list.push(item)
		})
		params = {
			sheetData: list,
			sheetFilter: ["username", "ip", "address", "browser", "os", "status", "errorMessage", "type", "createTime"],
			sheetHeader: ["用户名", "IP地址", "归属地", "浏览器", "操作系统", "登录状态", "错误信息", "登录类型", "登录日期"],
			fileName: "登录日志",
			sheetName: "登录日志"
		}
		ExportToExcel(params)
	}

	const listLoginLog = async (params: any) => {
		loginLogList = []
		return pageV3(getPageQuery(params)).then(res => {
			res?.data?.records?.forEach((item: TableColumns) => {
				item.status = statusEnum[item.status as '0'];
				item.type = typeEnum[item.type as 'password'];
				loginLogList.push(item);
			});
			return Promise.resolve({
				data: loginLogList,
				total: res.data.total,
				success: true,
			});
		})
	}

	const columns: ProColumns<TableColumns>[] = [
		{
			title: '序号',
			dataIndex: 'index',
			valueType: 'indexBorder',
			width: 60,
		},
		{
			title: '用户名',
			dataIndex: 'username'
		},
		{
			title: 'IP地址',
			dataIndex: 'ip'
		},
		{
			title: '归属地',
			dataIndex: 'address'
		},
		{
			title: '浏览器',
			dataIndex: 'browser'
		},
		{
			title: '操作系统',
			dataIndex: 'os'
		},
		{
			title: '登录状态',
			dataIndex: 'status',
			valueEnum: {
				0: {text: '登录成功', status: 'Success'},
				1: {text: '登录失败', status: 'Error'},
			},
		},
		{
			title: '错误信息',
			dataIndex: 'errorMessage'
		},
		{
			title: '登录类型',
			dataIndex: 'type',
			valueEnum: {
				password: {text: '用户名密码登录', status: 'Processing'},
				mobile: {text: '手机号登录', status: 'Default'},
				mail: {text: '邮箱登录', status: 'Success'},
				authorization_code: {text: '授权码登录', status: 'Error'}
			},
		},
		{
			title: '登录日期',
			key: 'createTime',
			dataIndex: 'createTime',
			valueType: 'dateTime',
			hideInSearch: true,
		},
		{
			title: '登录日期',
			dataIndex: 'createTime',
			valueType: 'dateRange',
			hideInTable: true,
			search: {
				transform: (value) => {
					return {
						startDate: value[0],
						endDate: value[1],
					};
				},
			}
		}
	];

	return (
		<ProTable<TableColumns>
			columns={columns}
			request={(params) => {
				// 表单搜索项会从 params 传入，传递给后端接口。
				return listLoginLog(params)
			}}
			rowKey="id"
			pagination={{
				showQuickJumper: true,
				showSizeChanger: false,
				pageSize: 10
			}}
			search={{
				layout: 'vertical',
				defaultCollapsed: true,
			}}
			toolBarRender={
				() => [
					<Button key="delete" danger ghost icon={<DeleteOutlined/>}>
						删除
					</Button>,
					<Button key="truncate" type="primary" danger icon={<DeleteOutlined/>}>
						清空
					</Button>,
					<Button key="export" type="primary" ghost icon={<ExportOutlined/>} onClick={exportToExcel}>
						导出
					</Button>,
					<Button key="exportAll" type="primary" icon={<ExportOutlined/>}>
						导出全部
					</Button>
				]
			}
			dateFormatter="string"
			toolbar={{
				title: '登录日志',
				tooltip: '登录日志',
			}}
		/>
	);
};
