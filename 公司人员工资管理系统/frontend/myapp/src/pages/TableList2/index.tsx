import {
  addDepartment, addSalaries,
  removeDepartment,
  removeSalaries,
  searchDepartments,
  searchSalaries,
  updateRule
} from '@/services/ant-design-pro/api';
import { PlusOutlined } from '@ant-design/icons';
import { ActionType, ProColumns, ProDescriptionsItemProps } from '@ant-design/pro-components';
import {
  FooterToolbar,
  ModalForm,
  PageContainer,
  ProDescriptions,
  ProFormText,
  ProTable,
} from '@ant-design/pro-components';
import '@umijs/max';
import { Button, Drawer, Input, message } from 'antd';
import React, { useRef, useState } from 'react';
import type { FormValueType } from './components/UpdateForm';
import UpdateForm from './components/UpdateForm';

const handleAdd = async (fields: API.CurrentDepartment) => {
  const hide = message.loading('正在添加');
  try {
    await addSalaries({
      ...fields,
    });
    hide();
    message.success('Added successfully');
    return true;
  } catch (error) {
    hide();
    message.error('Adding failed, please try again!');
    return false;
  }
};

const handleUpdate = async (fields: FormValueType) => {
  const hide = message.loading('Configuring');
  try {
    await updateRule({
      name: fields.name,
      desc: fields.desc,
      key: fields.key,
    });
    hide();
    message.success('Configuration is successful');
    return true;
  } catch (error) {
    hide();
    message.error('Configuration failed, please try again!');
    return false;
  }
};

const handleRemove = async (salaryId: string) => {
  const hide = message.loading('正在删除');
  if (!salaryId) return true;
  try {
    await removeSalaries({ name: salaryId });
    hide();
    message.success('Deleted successfully and will refresh soon');
    return true;
  } catch (error) {
    hide();
    message.error('Delete failed, please try again');
    return false;
  }
};

const TableList: React.FC = () => {
  const [createModalOpen, handleModalOpen] = useState<boolean>(false);
  const [updateModalOpen, handleUpdateModalOpen] = useState<boolean>(false);
  const [showDetail, setShowDetail] = useState<boolean>(false);
  const actionRef = useRef<ActionType>();
  const [currentRow, setCurrentRow] = useState<API.CurrentSalary>();
  const [selectedRowsState, setSelectedRows] = useState<API.CurrentSalary[]>([]);

  const columns: ProColumns<API.CurrentSalary>[] = [
    { title: '工资id', dataIndex: 'salaryId', search: true},
    { title: '员工id', dataIndex: 'employeeId', search: true },
    { title: '年份', dataIndex: 'year', search: true},
    { title: '月份', dataIndex: 'month', search: true},
    { title: '工作天数', dataIndex: 'workDays', search: false },
    { title: '实际工作天数', dataIndex: 'actualWorkDays',search: false },
    { title: '基本工资', dataIndex: 'basicSalary', search: false },
    { title: '岗位津贴', dataIndex: 'positionAllowance', search: false },
    { title: '午餐补贴', dataIndex: 'lunchAllowance', search: false },
    { title: '加班工资', dataIndex: 'overtimeSalary', search: false },
    { title: '全勤工资', dataIndex: 'fullAttendanceBonus', search: false },
    { title: '扣社保', dataIndex: 'socialInsurance', search: false },
    { title: '扣公积金', dataIndex: 'housingFund', search: false },
    { title: '扣税', dataIndex: 'tax', search: false },
    { title: '个人专项附加扣除', dataIndex: 'deductions', search: false },
    { title: '实发工资', dataIndex: 'netSalary', search: true },
    {
      title: '操作',
      valueType: 'option',
      key: 'option',
      render: (_, record) => [
        <a
          key="delete"
          onClick={async () => {
            const selectedNames = record.salaryId;
            await handleRemove(selectedNames);
            setSelectedRows([]);
            actionRef.current?.reloadAndRest?.();
          }}
        >
          删除
        </a>,
      ],
    },
  ];

  return (
    <PageContainer>
      <ProTable<API.CurrentSalary>
        headerTitle="查询表格"
        actionRef={actionRef}
        rowKey="key"
        search={{
          labelWidth: 'auto',
        }}
        toolBarRender={() => [
          <Button
            type="primary"
            key="primary"
            onClick={() => {
              handleModalOpen(true);
            }}
          >
            <PlusOutlined /> 新建/修改
          </Button>,
        ]}
        request={async (params, sort, filter) => {
          const salaryList = await searchSalaries({
            ...params,
          });
          return { data: salaryList };
        }}
        columns={columns}
      />
      <ModalForm
        title="月工资导入"
        width="400px"
        open={createModalOpen}
        onOpenChange={handleModalOpen}
        onFinish={async (value) => {
          const success = await handleAdd(value as API.CurrentSalary);
          if (success) {
            handleModalOpen(false);
            if (actionRef.current) {
              actionRef.current.reload();
            }
          }
        }}
      >
        <ProFormText
          rules={[
            {
              required: true,
              message: '员工id为必填项',
            },
          ]}
          label="员工id"
          width="md"
          name="employeeId"
        />
        <ProFormText
          rules={[
            {
              required: true,
              message: '年份为必填项',
            },
          ]}
          label="年份"
          width="md"
          name="year"
        />
        <ProFormText
          rules={[
            {
              required: true,
              message: '月份为必填项',
            },
          ]}
          label="月份"
          width="md"
          name="month"
        />
        <ProFormText
          rules={[
            {
              required: true,
              message: '工作天数为必填项',
            },
          ]}
          label="工作天数"
          width="md"
          name="workDays"
        />
        <ProFormText
          rules={[
            {
              required: true,
              message: '实际工作天数为必填项',
            },
          ]}
          label="实际工作天数"
          width="md"
          name="actualWorkDays"
        />
        <ProFormText
          rules={[
            {
              required: true,
              message: '基本工资为必填项',
            },
          ]}
          label="基本工资"
          width="md"
          name="basicSalary"
        />
        <ProFormText
          rules={[
            {
              required: true,
              message: '岗位津贴为必填项',
            },
          ]}
          label="岗位津贴"
          width="md"
          name="positionAllowance"
        />
        <ProFormText
          rules={[
            {
              required: true,
              message: '午餐补贴为必填项',
            },
          ]}
          label="午餐补贴"
          width="md"
          name="lunchAllowance"
        />
        <ProFormText
          label="加班工资"
          width="md"
          name="overtimeSalary"
        />
        <ProFormText
          label="全勤工资"
          width="md"
          name="fullAttendanceBonus"
        />
        <ProFormText
          rules={[
            {
              required: true,
              message: '扣社保为必填项',
            },
          ]}
          label="扣社保"
          width="md"
          name="socialInsurance"
        />
        <ProFormText
          rules={[
            {
              required: true,
              message: '扣公积金为必填项',
            },
          ]}
          label="扣公积金"
          width="md"
          name="housingFund"
        />
        <ProFormText
          rules={[
            {
              required: true,
              message: '个人专项附加扣除为必填项',
            },
          ]}
          label="个人专项附加扣除"
          width="md"
          name="deductions"
        />
      </ModalForm>
      <UpdateForm
        onSubmit={async (value) => {
          const success = await handleUpdate(value);
          if (success) {
            handleUpdateModalOpen(false);
            setCurrentRow(undefined);
            if (actionRef.current) {
              actionRef.current.reload();
            }
          }
        }}
        onCancel={() => {
          handleUpdateModalOpen(false);
          if (!showDetail) {
            setCurrentRow(undefined);
          }
        }}
        updateModalOpen={updateModalOpen}
        values={currentRow || {}}
      />
      <Drawer
        width={600}
        open={showDetail}
        onClose={() => {
          setCurrentRow(undefined);
          setShowDetail(false);
        }}
        closable={false}
      >
        {currentRow?.departmentName && (
          <ProDescriptions<API.CurrentSalary>
            column={2}
            title={currentRow?.departmentName}
            request={async () => ({ data: currentRow || {} })}
            params={{ id: currentRow?.departmentName }}
            columns={columns as ProDescriptionsItemProps<API.CurrentSalary>[]}
          />
        )}
      </Drawer>
    </PageContainer>
  );
};

export default TableList;

